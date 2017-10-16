package com.zwp.myappframework.sample.http;


import com.zwp.myappframework.framwork.bean.HttpResultBean;
import com.zwp.myappframework.sample.model.WeatherResult;

import retrofit2.http.GET;
import rx.Observable;


/**
 * Created by zwp on 2017/5/23.
 * 请求接口地址
 */

public interface ApiStores {


    @GET("index?format=2&cityname=%E8%8B%8F%E5%B7%9E&key=bd25e9ba796edced93dbdcda6d3809f7")
    Observable<HttpResultBean<WeatherResult>> getWeatherData();


}
