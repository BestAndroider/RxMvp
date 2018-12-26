package project.hx.com.rxjava.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import project.hx.com.rxjava.net.exception.NetException;

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        success(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof NetException.ResponseException) {
            error((NetException.ResponseException) e);
        } else {
            onError(new NetException.ResponseException(e, NetException.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onComplete() {
    }

    public abstract void success(T t);

    public abstract void error(NetException.ResponseException e);

}
