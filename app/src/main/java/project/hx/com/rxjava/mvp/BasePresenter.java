package project.hx.com.rxjava.mvp;

import project.hx.com.rxjava.mvp.view.BaseView;

public abstract class BasePresenter<V extends BaseView> {
    protected V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
    }
}
