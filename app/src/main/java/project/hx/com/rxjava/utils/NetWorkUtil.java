package project.hx.com.rxjava.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import static android.content.Context.CONNECTIVITY_SERVICE;


/**
 * 网络信息管理类
 *
 * @author 胡鑫
 */
public class NetWorkUtil {

    private static String TAG = "NetWorkHelper";

    public static Uri uri = Uri.parse("content://telephony/carriers");

    /**
     * 判断MOBILE网络是否可用
     *
     * @param context
     * @return
     * @throws Exception
     */
    /*public static boolean isMobileDataEnable(Context context) throws Exception {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        boolean isMobileDataEnable;

        isMobileDataEnable =
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

        return isMobileDataEnable;
    }*/

    /**
     * 判断是否有网络连接
     */
    /*public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        boolean isConnected = false;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isAvailable()) {
            isConnected = activeNetwork.isConnectedOrConnecting();
            switch (activeNetwork.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    break;
            }
        } else {
        }

        return isConnected;
    }
*/
}
