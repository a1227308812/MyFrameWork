package com.zwp.myappframework.sample.http;

import android.text.TextUtils;

import com.zwp.myappframework.Config;
import com.zwp.myappframework.MyApplication;
import com.zwp.myappframework.framwork.utils.SharePreferencesUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求头部统一添加authKey
 */
public class AuthKeyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取请求
        Request request = chain.request();
        //请求头部统一添加authKey
        String encryptedAuthKey = (String) SharePreferencesUtils.get(MyApplication.getInstance(),
                Config.DEFAULT_SP_FILE_NAME, SPKeys.AUTH_KEY, "");
        if(!TextUtils.isEmpty(encryptedAuthKey)){
            try {
                String authKey= AESCrypt.decrypt(SPKeys.AUTH_KEY_PWD,encryptedAuthKey);
                request=request.newBuilder().addHeader("auth_key",authKey)
                        .build();
            }catch (GeneralSecurityException e){
            e.printStackTrace();
        }
        }
        //返回结果
        Response originalResponse = chain.proceed(request);
        int code=originalResponse.code();
//        Logger.i("Response code -------------:"+code);
        if (code == 401){
//            MyApplication.getInstance().intentActivity();
        }
        return originalResponse;
    }
}
