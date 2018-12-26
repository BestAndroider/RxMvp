package project.hx.com.rxjava.activity;

import android.os.Bundle;
import android.util.Log;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import project.hx.com.rxjava.R;
import project.hx.com.rxjava.WanAndroidService;
import project.hx.com.rxjava.base.BaseActivity;
import project.hx.com.rxjava.data.bean.PublicAccountBean;
import project.hx.com.rxjava.mvp.contract.MainContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends BaseActivity<MainContract.Prensenter> implements MainContract.IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.getPublicAccountList();
        /*Retrofit retrofit = new Retrofit.Builder()
                //设置baseUrl,baseUrl+接口中配置的地址组成真正的请求地址。
                .baseUrl("http://wanandroid.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()) // 支持Gson解析
                .client(new OkHttpClient())
                .build();

        WanAndroidService service = retrofit.create(WanAndroidService.class);
        service.getPublicAccountList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PublicAccountBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PublicAccountBean publicAccountBean) {
                        //请求成功
                        for (int i = 0; i < publicAccountBean.getData().size(); i++) {
                            Log.e("data----------", publicAccountBean.getData().get(i).getName());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        //失败
                    }

                    @Override
                    public void onComplete() {
                    }
                });*/
    }

    @Override
    public MainContract.Prensenter createPresenter() {
        return new MainContract.Prensenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void doSomething() {

    }
}
