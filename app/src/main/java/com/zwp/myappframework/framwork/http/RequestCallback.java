package com.zwp.myappframework.framwork.http;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by zwp on 2017/5/25.
 * 统一封装回调控制
 */

public abstract class RequestCallback<T> extends Subscriber<T>{


    public abstract void onFinish();

    public abstract void onException(Throwable e);

    public abstract void onResponse(T t);


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
        Log.e("ccc","RequestCallback  onCompleted");
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        Log.e("ccc","RequestCallback  onError");
        onException(e);
    }

    @Override
    public void onNext(T t) {
        Log.e("ccc","RequestCallback  onNext");
        onResponse(t);
    }
}
