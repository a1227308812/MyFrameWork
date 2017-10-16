//package com.zwp.myappframework.test;
//
//import android.os.Bundle;
//import android.support.v7.app.ActionBar;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.MenuItem;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//
//import com.zwp.myappframework.Config;
//import com.zwp.myappframework.MyApplication;
//import com.zwp.myappframework.R;
//import com.zwp.myappframework.framwork.base.ToolBarActivity;
//import com.zwp.myappframework.framwork.utils.SharePreferencesUtils;
//import com.zwp.myappframework.sample.http.AESCrypt;
//import com.zwp.myappframework.sample.http.SPKeys;
//
//import java.security.GeneralSecurityException;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * 网页显示页面
// */
//public class WebActivity extends ToolBarActivity {
//
//    //组件
//    @BindView(R.id.web_view)
//    WebView webView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_web);
//        ButterKnife.bind(this);
////        Intent intent = getIntent();
////        String url = intent.getStringExtra("url");
//        //请求头部统一添加authKey
//        String encryptedAuthKey = (String) SharePreferencesUtils.get(MyApplication.getInstance(),
//                Config.DEFAULT_SP_FILE_NAME, SPKeys.AUTH_KEY, "");
//        String authKey = "";
//        try {
//            authKey = AESCrypt.decrypt(SPKeys.AUTH_KEY_PWD, encryptedAuthKey);
//            Log.e("ccc",""+authKey);
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        }
////        String url = "http://www.baidu.com";
//        String url = Config.API_URL + "/workFlow/viewSpFlow?sid="+authKey+"&instanceId=533";
//
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);//开启JavaScript
//        webView.setWebViewClient(new CustomWebViewClient());
//        webView.setWebChromeClient(new CustomWebChromeClient());
//        webView.loadUrl(url);
//    }
//
//    class CustomWebViewClient extends WebViewClient {
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            return false;
//        }
//
//    }
//
//    class CustomWebChromeClient extends WebChromeClient {
//        @Override
//        public void onReceivedTitle(WebView view, String title) {
//            super.onReceivedTitle(view, title);
//            //设置标题
//            ActionBar actionBar = getSupportActionBar();
//            if (actionBar != null) {
//                actionBar.setTitle(title);
//            }
//        }
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
//            webView.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            //返回上一个界面
//            finish();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
