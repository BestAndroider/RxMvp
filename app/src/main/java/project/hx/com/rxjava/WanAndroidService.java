package project.hx.com.rxjava;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import project.hx.com.rxjava.data.bean.PublicAccountBean;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WanAndroidService {
    /**
     * 获取公众号列表
     * @return SessionBean
     */
    @GET("wxarticle/chapters/json")
    Observable<PublicAccountBean> getPublicAccountList();
}
