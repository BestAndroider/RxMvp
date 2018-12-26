package project.hx.com.rxjava.net.manager;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import project.hx.com.rxjava.BuildConfig;
import project.hx.com.rxjava.net.interceptor.BaseInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitManager {
    private Retrofit mRetrofit;

    private static class InstanceHelper {
        static RetrofitManager instance = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return InstanceHelper.instance;
    }

    private RetrofitManager() {
        mRetrofit = new Retrofit.Builder()
                //设置baseUrl
                .baseUrl("http://wanandroid.com/")
                //设置OkHttpClient对象
                .client(createOkhttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .addConverterFactory(GsonConverterFactory.create()) // 支持Gson解析
                .addConverterFactory(ScalarsConverterFactory.create())//支持字符串
                .build();
    }

    /*
    * 创建OkHttpClient对象。Retrofit底层基于OkHttpClient进行网络请求。
    * */
    private OkHttpClient createOkhttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder()
                //设置连接超时时间
                .connectTimeout(30, TimeUnit.SECONDS)
                //设置读取超时时间
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                //添加日志过滤器
                .addInterceptor(httpLoggingInterceptor)
                //添加BaseInterceptor过滤器
                .addInterceptor(new BaseInterceptor())
                .build();
    }

    public <T> T createApi(final Class<T> service) {
        return mRetrofit.create(service);
    }
}
