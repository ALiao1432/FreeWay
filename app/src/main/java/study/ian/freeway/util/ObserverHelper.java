package study.ian.freeway.util;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import study.ian.freeway.service.ServiceBuilder;

public class ObserverHelper {

    private static final ObservableTransformer observableHelper =
            upstream -> upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .retryWhen(t -> ServiceBuilder.getConnectStateObservable());

    public static <T> ObservableTransformer<T, T> applyHelper() {
        return (ObservableTransformer<T, T>) observableHelper;
    }
}
