package study.ian.freeway.service;

import org.jsoup.nodes.Document;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import study.ian.freeway.model.notify.Notify;
import study.ian.freeway.model.road.Road;

public interface FreewayService {

    @GET("api/getRoadInformation?action=road")
    Observable<Response<Road>> getRoadInfo(
            @Query("freewayid") int freewayId,
            @Query("from_milepost") int fromMilepost,
            @Query("end_milepost") int endMilepost
    );

    @GET("api/getRoadInformation?action=notify")
    Observable<Response<Notify>> getRoadNotify(
            @Query("freewayid") int freewayId,
            @Query("from_milepost") int fromMilepost,
            @Query("end_milepost") int endMilepost
    );

    @GET("n_notify")
    Observable<Response<String>> getRoadName();
}
