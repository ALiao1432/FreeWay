package study.ian.freeway.service;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.processors.PublishProcessor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import study.ian.networkstateutil.ConnectionType;
import study.ian.networkstateutil.NetworkStateChangeListener;
import study.ian.networkstateutil.NetworkStateUtil;

public class ServiceBuilder {

    private final static String API_BASE_URL = "https://1968.freeway.gov.tw/";

    private static final Retrofit roadRetrofit = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    private static PublishProcessor<ConnectionType> connectionTypeProcessor = PublishProcessor.create();

    private ServiceBuilder() {}

    public static <T> T getRoadService(Class<T> tClass) {
        return roadRetrofit.create(tClass);
    }

    public static void watchNetworkState(Context context) {
        new NetworkStateUtil(context, new NetworkStateChangeListener() {
            @Override
            public void onConnected(ConnectionType connectionType) {
                connectionTypeProcessor.onNext(connectionType);
            }

            @Override
            public void onDisconnected() {
            }
        });
    }

    public static Observable<ConnectionType> getConnectStateObservable() {
        return connectionTypeProcessor.toObservable();
    }
}
