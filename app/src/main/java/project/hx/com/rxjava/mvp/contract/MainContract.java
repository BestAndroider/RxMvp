package project.hx.com.rxjava.mvp.contract;

import com.google.gson.Gson;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import project.hx.com.rxjava.base.BaseObserver;
import project.hx.com.rxjava.data.bean.PublicAccountBean;
import project.hx.com.rxjava.mvp.BasePresenter;
import project.hx.com.rxjava.mvp.view.BaseView;
import project.hx.com.rxjava.net.api.API;
import project.hx.com.rxjava.net.exception.NetException;
import project.hx.com.rxjava.net.manager.RetrofitManager;

public class MainContract {
    /*
     * Activity实现的接口
     * */
    public interface IView extends BaseView {
        void doSomething();
    }

    public static class Prensenter extends BasePresenter<IView> {
        private API api;
        private Gson gson;

        public Prensenter(IView mView) {
            super(mView);
            api = RetrofitManager.getInstance().createApi(API.class);
            gson = new Gson();
        }

        public void getPublicAccountList() {
            Map<String,String> map = new HashMap<>();
            api.getPublicAccountList(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(RxLifecycle.bindUntilEvent(mView.getLifeCycleSubject(), ActivityEvent.DESTROY))
                    .doOnSubscribe(disposable -> mView.showLoading())
                    .doFinally(() -> mView.hideLoading())
                    .subscribe(new BaseObserver<PublicAccountBean>() {
                        @Override
                        public void success(PublicAccountBean publicAccountBean) {
                            //成功
                        }

                        @Override
                        public void error(NetException.ResponseException e) {
                            //失败。
                        }
                    });
        }
    }
}
