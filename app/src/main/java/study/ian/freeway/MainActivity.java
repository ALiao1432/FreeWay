package study.ian.freeway;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.reactivex.processors.PublishProcessor;
import study.ian.freeway.adapter.RoadAdapter;
import study.ian.freeway.util.ObserverHelper;
import study.ian.freeway.view.BottomTextSelectView;
import study.ian.morphviewlib.MorphView;
import study.ian.networkstateutil.ConnectionType;
import study.ian.networkstateutil.RxNetworkStateUtil;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private RoadAdapter roadAdapter;
    private RecyclerView roadRecyclerView;
    private BottomTextSelectView textSelectView;
    private MorphView loadingView;
    private PublishProcessor<String> roadListProcessor = PublishProcessor.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();

        new RxNetworkStateUtil(this).getNetworkStateObservable()
                .filter(connectionType -> connectionType != ConnectionType.NO_NETWORK)
                .doOnEach(connectionTypeNotification -> loadingView.performInfiniteAnimation(R.drawable.vd_loading_sin_1, R.drawable.vd_loading_sin_2))
                .doOnNext(connectionType -> getGeneratedHTML())
                .flatMap(connectionType -> roadListProcessor.toObservable())
                .compose(ObserverHelper.applyHelper())
                .doOnNext(this::getRoadList)
                .doOnError(t -> Log.d(TAG, "onCreate: getGeneratedHTML error : " + t))
                .subscribe();
    }

    private void findViews() {
        roadRecyclerView = findViewById(R.id.recyclerViewRoad);
        textSelectView = findViewById(R.id.textSelectView);
        loadingView = findViewById(R.id.loadingMorphView);
    }

    private void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        roadAdapter = new RoadAdapter(this);
        roadRecyclerView.setAdapter(roadAdapter);
        roadRecyclerView.setLayoutManager(linearLayoutManager);

        textSelectView.addText(getString(R.string.highway_speed), getString(R.string.highway_notify));

        loadingView.setPaintWidth(50);
        loadingView.setPaintColor(getColor(R.color.colorAccent));
        loadingView.setCurrentId(R.drawable.vd_loading_sin_1);
        loadingView.setSize(1080, 1920);
        loadingView.setAnimationDuration(500);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void getGeneratedHTML() {
        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }
        });
        webView.loadUrl("https://1968.freeway.gov.tw/n_notify");
    }

    private void getRoadList(String html) {
        Document doc = Jsoup.parse(html);
        Element freeway = doc.getElementById("freeway");
        Elements roads = freeway.children();

        loadingView.stopInfiniteAnimation();
        loadingView.setVisibility(View.GONE);

        roadAdapter.addRoadList(roads);
    }

    private void showLongLog(String log) {
        int MAX_LOG_SIZE = 2000;

        if (log.length() > MAX_LOG_SIZE) {
            Log.d(TAG, "Display Log : " + log.substring(0, MAX_LOG_SIZE));
            showLongLog(log.substring(MAX_LOG_SIZE));
        } else {
            Log.d(TAG, "Display Log : " + log);
        }
    }

    class MyJavaScriptInterface {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html) {
            roadListProcessor.onNext(html);
        }
    }
}
