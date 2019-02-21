package study.ian.freeway.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import study.ian.freeway.R;
import study.ian.freeway.util.BottomTextSelectListener;

public class BottomTextSelectView extends View {

    private final String TAG = "BottomTextSelectView";

    public final static int ANIMATION_ALPHA = 0;
    public final static int ANIMATION_POSITION = 1;
    public final static int ANIMATION_ALPHA_POSITION = 2;

    private BottomTextSelectListener selectListener;
    private Paint textPaint;
    private Paint selectPaint;
    private String[] texts;
    private List<RectF> rectFList = new ArrayList<>();
    private Rect textRect = new Rect();
    private RectF selectRectF = new RectF();
    private ValueAnimator alphaAnimator;
    private ValueAnimator positionLeftAnimation;
    private ValueAnimator positionRightAnimation;
    private int animType = ANIMATION_ALPHA_POSITION;
    private int selectItem = 0;
    private int textColor;
    private int selectColor;


    @SuppressLint("ClickableViewAccessibility")
    public BottomTextSelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        textColor = context.getColor(R.color.colorPrimary);
        selectColor = context.getColor(R.color.colorAccent);

        initPaints();
        initAnimator();

        setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    for (int i = 0; i < rectFList.size(); i++) {
                        if (rectFList.get(i).contains(event.getX(), event.getY())) {
                            performAnim(selectItem, i);
                            selectItem = i;

                            if (selectListener != null) {
                                selectListener.onItemSelected(selectItem);
                            }
                            postInvalidate();
                        }
                    }
                    break;
            }
            return true;
        });
    }

    private void performAnim(int start, int end) {
        switch (animType) {
            case ANIMATION_ALPHA:
                alphaAnimator.start();
                break;
            case ANIMATION_POSITION:
                positionLeftAnimation.setFloatValues(rectFList.get(start).left, rectFList.get(end).left);
                positionRightAnimation.setFloatValues(rectFList.get(start).right, rectFList.get(end).right);
                positionLeftAnimation.start();
                positionRightAnimation.start();
                break;
            case ANIMATION_ALPHA_POSITION:
                positionLeftAnimation.setFloatValues(rectFList.get(start).left, rectFList.get(end).left);
                positionRightAnimation.setFloatValues(rectFList.get(start).right, rectFList.get(end).right);
                positionLeftAnimation.start();
                positionRightAnimation.start();
                alphaAnimator.start();
            default:
                break;
        }
    }

    private void initPaints() {
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(60);
        textPaint.setColor(textColor);

        selectPaint = new Paint();
        selectPaint.setAntiAlias(true);
        selectPaint.setStrokeWidth(8);
        selectPaint.setColor(selectColor);
        selectPaint.setStyle(Paint.Style.STROKE);
    }

    private void initAnimator() {
        long ANIM_DURATION = 240;

        alphaAnimator = new ValueAnimator();
        alphaAnimator.setIntValues(0, 255);
        alphaAnimator.setDuration(ANIM_DURATION);
        alphaAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimator.addUpdateListener(animation -> {
            selectPaint.setAlpha((int) animation.getAnimatedValue());
            postInvalidate();
        });

        positionLeftAnimation = new ValueAnimator();
        positionLeftAnimation.setInterpolator(new OvershootInterpolator());
        positionLeftAnimation.setDuration(ANIM_DURATION);
        positionLeftAnimation.addUpdateListener(animation -> {
            selectRectF.left = (float) animation.getAnimatedValue();
            postInvalidate();
        });

        positionRightAnimation = new ValueAnimator();
        positionRightAnimation.setInterpolator(new OvershootInterpolator());
        positionRightAnimation.setDuration(ANIM_DURATION);
        positionRightAnimation.addUpdateListener(animation -> {
            selectRectF.right = (float) animation.getAnimatedValue();
            postInvalidate();
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, Math.round(height * .1f));
    }

    public void addText(String... texts) {
        this.texts = texts;
        rectFList.clear();
        postInvalidate();
    }

    public void setAnimType(int animType) {
        this.animType = animType;
    }

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectListener(BottomTextSelectListener selectListener) {
        this.selectListener = selectListener;
    }

    public void setTextSize(float size) {
        textPaint.setTextSize(size);
    }

    public void setSelectPaintWidth(long width) {
        selectPaint.setStrokeWidth(width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < texts.length; i++) {
            textPaint.getTextBounds(texts[i], 0, texts[i].length(), textRect);
            canvas.drawText(
                    texts[i],
                    ((float) getWidth()) * i / texts.length + (((float) getWidth()) / texts.length - textRect.width()) * .5f,
                    (getHeight() + textRect.height()) * .5f,
                    textPaint
            );

            textRect.offset(
                    (int) (((float) getWidth()) * i / texts.length + (((float) getWidth()) / texts.length - textRect.width()) * .5f),
                    (int) ((getHeight() + textRect.height()) * .5f)
            );

            if (rectFList.size() < texts.length) {
                selectRectF.set(textRect);
                scaleRectF(selectRectF);
                rectFList.add(new RectF(selectRectF));
            }

            if (selectItem == i) {
                canvas.drawRoundRect(selectRectF, 50, 50, selectPaint);
            }
        }
    }

    private void scaleRectF(@NotNull RectF rectF) {
        final float H_SCALE_FACTOR = 0.18f;
        final float V_SCALE_FACTOR = 0.24f;

        rectF.left = rectF.left - rectF.width() * H_SCALE_FACTOR;
        rectF.top = rectF.top - rectF.height() * V_SCALE_FACTOR;
        rectF.right = rectF.right + rectF.width() * H_SCALE_FACTOR;
        rectF.bottom = rectF.bottom + rectF.height() * V_SCALE_FACTOR;
    }
}
