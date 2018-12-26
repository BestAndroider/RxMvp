package project.hx.com.rxjava.mvp.view;

import io.reactivex.subjects.BehaviorSubject;

public interface BaseView{
    /**
     * Show loading
     */
    void showLoading();

    /**
     * Hide loading
     */
    void hideLoading();

    /**
     * Show Msg
     */
    void showMsg(String msg);

    <R> BehaviorSubject<R> getLifeCycleSubject();
}
