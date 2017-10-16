package com.zwp.myappframework.framwork.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zwp.myappframework.MyApplication;
import com.zwp.myappframework.framwork.permission.PermissionCallback;

import java.util.List;


/**
 * Created by zwp on 2017/5/23.
 */

public class BaseActivity extends AutoLayoutActivity implements PermissionCallback {
    public static final int REQUEST_CODE_SETTING = 111;
    MyApplication app;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (MyApplication) this.getApplication();
        //添加Activity到堆栈
        app.addActivity(this);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    /**
     * 状态栏透明只有Android 4.4 以上才支持
     */
    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.setAttributes(layoutParams);
        }
    }

    /**
     * 将某个View设置为返回键
     *
     * @param view
     */
    protected void setToBack(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        //取消订阅
        if (MyApplication.getInstance().subscriptionList != null){
            MyApplication.getInstance().subscriptionList.unsubscribe();//取消订阅  当前页面的所有订阅关系
            MyApplication.getInstance().subscriptionList.clear();//清空订阅集合
        }
        MyApplication.getInstance().finishActivity(this);
        super.onDestroy();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 只需要调用这一句，其它的交给AndPermission吧，最后一个参数是PermissionListener。
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults,listener);
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //权限监听
    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            BaseActivity.this.getPermissionSucceed(requestCode,grantedPermissions);
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            BaseActivity.this.getPermissionFail(requestCode,deniedPermissions);
            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(BaseActivity.this, deniedPermissions)) {
                // 第一种：用默认的提示语。
//                AndPermission.defaultSettingDialog(BaseActivity.this, REQUEST_CODE_SETTING).show();

                 //第二种：用自定义的提示语。
                 AndPermission.defaultSettingDialog(BaseActivity.this, REQUEST_CODE_SETTING)
                 .setTitle("权限申请失败")
                 .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
                 .setPositiveButton("好，去设置")
                 .show();

                // 第三种：自定义dialog样式。
                // SettingService settingService =
                //    AndPermission.defineSettingDialog(this, REQUEST_CODE_SETTING);
                // 你的dialog点击了确定调用：
                // settingService.execute();
                // 你的dialog点击了取消调用：
                // settingService.cancel();
            }
        }
    };

    @Override
    public void getPermissionSucceed(int requestCode, List<String> grantedPermissions) {
        Log.e("ccc","base getPermissionSucceed");
    }

    @Override
    public void getPermissionFail(int requestCode, List<String> deniedPermissions) {
        Log.e("ccc","base getPermissionFail");
    }
}
