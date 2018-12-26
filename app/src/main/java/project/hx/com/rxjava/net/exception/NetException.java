package project.hx.com.rxjava.net.exception;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.text.ParseException;

import retrofit2.HttpException;

public class NetException {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponseException handleException(Throwable e) {
        //转换成ResponseException,根据状态码判定错误信息
        ResponseException ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            /**
             * 传入状态码，根据状态码判定错误信息
             */
            ex = new ResponseException(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.message = "未验证";
                    break;
                case FORBIDDEN:
                    ex.message = "服务禁止访问";
                    break;
                case NOT_FOUND:
                    ex.message = "服务不存在";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "请求超时";
                    break;
                case GATEWAY_TIMEOUT:
                    ex.message = "网关超时";
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.message = "服务器内部错误";
                    break;
                case BAD_GATEWAY:
                    ex.message = "请求失败";
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.message = "请求失败";
                    break;
                default:
                    ex.message = "请求失败";
                    break;
            }
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ResponseException(e, ERROR.PARSE_ERROR);
            ex.message = "数据解析异常";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponseException(e, ERROR.NETWORD_ERROR);
            ex.message = "请求失败";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponseException(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else {
            ex = new ResponseException(e, ERROR.UNKNOWN);
            ex.message = "未知错误";
            return ex;
        }
    }

    /**
     * 约定异常
     */
    public static class ERROR {
        /**
         * 自定义异常
         */
        private static final int UNAUTHORIZED = 401;//请求用户进行身份验证
        private static final int UNREQUEST = 403;//服务器理解请求客户端的请求，但是拒绝执行此请求
        private static final int UNFINDSOURCE = 404;//服务器无法根据客户端的请求找到资源
        private static final int SEVERERROR = 500;//服务器内部错误，无法完成请求。
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;
    }


    /**
     * 统一异常类，便于处理
     */
    public static class ResponseException extends Exception {
        public int code;
        public String message;

        public ResponseException(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }
}
