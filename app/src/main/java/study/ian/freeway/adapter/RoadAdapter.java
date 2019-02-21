package study.ian.freeway.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import study.ian.freeway.R;

public class RoadAdapter extends RecyclerView.Adapter<RoadAdapter.RoadHolder> {

    private final String TAG = "RoadAdapter";

    private Context context;
    private List<Element> roadList = new ArrayList<>();

    public RoadAdapter(Context context) {
        this.context = context;
    }

    public void addRoadList(List<Element> list) {
        if (roadList.size() == 0) {
            roadList.addAll(list);
            roadList.remove(0);
            this.notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RoadHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RoadHolder(LayoutInflater.from(context).inflate(R.layout.holder_road_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoadHolder roadHolder, int i) {
        roadHolder.button.setText(roadList.get(i).text());
    }

    @Override
    public int getItemCount() {
        return roadList.size();
    }

    class RoadHolder extends RecyclerView.ViewHolder {

        private Button button;

        RoadHolder(@NonNull View itemView) {
            super(itemView);

            button = itemView.findViewById(R.id.roadButton);
        }
    }
}
