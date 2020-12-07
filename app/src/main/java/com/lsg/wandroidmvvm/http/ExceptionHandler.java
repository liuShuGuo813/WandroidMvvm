package com.lsg.wandroidmvvm.http;

import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.load.HttpException;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.lsg.wandroidmvvm.app.APP;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;


/**
 * create by lsg in 2019/12/23
 *
 **/
public class ExceptionHandler {
    private static final String TAG = "ExceptionHandler";
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    private static final int BUSINESS_EXCEPTION = 450;
    private static final int BIZ_TO_LOGIN = 4002;


    public static void handleException(Throwable e) {
        String errmsg = null;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.getStatusCode()) {
                case BUSINESS_EXCEPTION:
//                    try {
//                        HttpExceptionBean httpExceptionBean =
//                                new Gson().fromJson(httpException.response().errorBody().string(), HttpExceptionBean.class);
//                        errmsg = httpExceptionBean.getMessage();
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                        errmsg = httpException.getMessage();
//                    }
                    break;
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    errmsg = "请求失败";
                    errmsg = errmsg + ":" + httpException.getStatusCode();
                    break;
            }
        } else if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            errmsg = exception.getErrmsg();
            // 服务端返回的错误码：40301=token失效，重新登录
            int errCode = exception.getErrcode();
            // 根据业务逻辑处理异常信息，如：token失效，跳转至登录界面
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            errmsg = "解析错误";
        } else if (e instanceof ConnectException) {
            errmsg = "网络连接失败,请稍后重试";
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            e.printStackTrace();
            errmsg = "证书验证失败";
            Log.d(TAG, "handleException: " + e.getMessage());
        } else if (e instanceof ConnectTimeoutException) {
            errmsg = "网络连接超时";
        } else if (e instanceof java.net.SocketTimeoutException) {
            errmsg = "连接超时";
        } else {
            errmsg = "网络连接异常,请稍后重试";
        }
        Toast.makeText(APP.getInstance(), errmsg, Toast.LENGTH_LONG).show();
    }

}
