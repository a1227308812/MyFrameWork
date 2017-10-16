package com.zwp.myappframework.sample.http;

import com.zwp.myappframework.Config;
import com.zwp.myappframework.MyApplication;
import com.zwp.myappframework.framwork.bean.HttpResultBean;
import com.zwp.myappframework.framwork.http.RequestCallback;
import com.zwp.myappframework.sample.model.WeatherResult;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zwp on 2017/5/23.
 * Retrofit接口和返回数据管理类
 */

public class AppClient {
    //请求超时时间
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;

    private ApiStores apiStores;

    public AppClient() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        //超时时间
        clientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //设置拦截器 需要添加头部时才添加
//        clientBuilder.addInterceptor(new AuthKeyInterceptor());
//        clientBuilder.addInterceptor(new LoggingInterceptor());

        retrofit = new Retrofit.Builder()
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Config.API_BASE_URL)//基础地址
                .build();

        apiStores = retrofit.create(ApiStores.class);

    }

//    //在访问AppClient时创建单例
//    private static class SingletonHolder {
//        private static final AppClient INSTANCE = new AppClient();
//    }
//
//    //获取单例
//    public static AppClient getInstance() {
//        return SingletonHolder.INSTANCE;
//    }
    //每次都新建一个
    public static AppClient getInstance() {
        return new AppClient();
    }

    /**
     * 订阅（链接观察者与被观察者）
     *
     * @param o   被观察者
     * @param s   观察者
     * @param <T>
     */
    private <T> void toSubscribe(Observable<T> o, RequestCallback<T> s) {

        Subscription subscription = o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
        //把订阅关系添加到订阅集合中
        MyApplication.getInstance().subscriptionList.add(subscription);
    }

    /**
     * 统一处理HttpResult的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>
     */
    private class HttpResultFunc<T> implements Func1<HttpResultBean<T>, T> {

        @Override
        public T call(final HttpResultBean<T> httpResult) {
            return httpResult.getResult();
        }
    }

    /**
     * 获取天气信息
     *
     * @param subscriber
     */
    public void getWeatherData(RequestCallback subscriber) {
        final Observable<WeatherResult> observable = apiStores.getWeatherData().map(new HttpResultFunc<WeatherResult>());
        //连接数据源和观察者
        toSubscribe(observable, subscriber);
    }

}
