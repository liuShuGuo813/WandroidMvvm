package com.lsg.wandroidmvvm.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.lsg.wandroidmvvm.R;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.ssl.SSLSocketFactoryImpl;
import rxhttp.wrapper.ssl.X509TrustManagerImpl;

/**
 * Created by lsg on 2020/10/26
 */
public class APP extends Application {

    private static APP app;

    public static APP getInstance(){
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initRxHttp();
    }

    /**
     * 初始化Rxhttp请求框架
     * GitHub：https://github.com/liujingxing/okhttp-RxHttp
     */
    private void initRxHttp() {
        X509TrustManager trustAllCert = new X509TrustManagerImpl();
        SSLSocketFactory sslSocketFactory = new SSLSocketFactoryImpl(trustAllCert);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory, trustAllCert) //添加信任证书
                .hostnameVerifier((hostname, session) -> true) //忽略host验证
                .build();

        //设置debug模式，默认为false，设置为true后，发请求，过滤"RxHttp"能看到请求日志
        RxHttp.init(okHttpClient,true);
    }

    static {
//        ClassicsHeader.REFRESH_HEADER_PULLING = "下拉可以刷新";
//        ClassicsHeader.REFRESH_HEADER_REFRESHING = "正在刷新...";
//        ClassicsHeader.REFRESH_HEADER_LOADING = "正在加载...";
//        ClassicsHeader.REFRESH_HEADER_RELEASE = "释放立即刷新";
//        ClassicsHeader.REFRESH_HEADER_FINISH = "刷新完成";
//        ClassicsHeader.REFRESH_HEADER_FAILED = "刷新失败";
//        ClassicsHeader.REFRESH_HEADER_SECONDARY = "释放进入二楼";
//        ClassicsHeader.REFRESH_HEADER_UPDATE = "上次更新 M-d HH:mm";
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);
            }
        });
//        ClassicsFooter.REFRESH_FOOTER_PULLING = "上拉加载更多";
//        ClassicsFooter.REFRESH_FOOTER_RELEASE = "释放立即加载";
//        ClassicsFooter.REFRESH_FOOTER_REFRESHING = "正在刷新...";
//        ClassicsFooter.REFRESH_FOOTER_LOADING = "正在加载...";
//        ClassicsFooter.REFRESH_FOOTER_FINISH = "加载完成";
//        ClassicsFooter.REFRESH_FOOTER_FAILED = "加载失败";
//        ClassicsFooter.REFRESH_FOOTER_NOTHING = "没有更多数据了";
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context);
            }
        });
    }
}
