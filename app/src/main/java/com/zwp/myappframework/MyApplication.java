package com.zwp.myappframework;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.liulishuo.filedownloader.FileDownloader;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.zwp.myappframework.framwork.utils.SharePreferencesUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import rx.internal.util.SubscriptionList;

/**
 * Created by zwp on 2017/5/23.
 */

public class MyApplication extends Application {
    //实例对象
    private static MyApplication mInstance;

    //Activity栈
    private static Stack<Activity> activityStack;

    //订阅集合
    public SubscriptionList subscriptionList ;

    public MyApplication() {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        if (subscriptionList == null){
            subscriptionList = new SubscriptionList();
        }
    }
    /**
     * 获取实例对象
     *
     * @return
     */
    public static synchronized MyApplication getInstance() {
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    public static Context getAppContext(){
        return mInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        //初始化下载组件
        FileDownloader.init(getApplicationContext());

        //初始化全聚异常捕获  想要在自己的自定义异常捕获之后再使用bugly上传崩溃日志，就需要把自己的异常捕获初始化放在bugly初始化之前
        CrashHandler crashHandler =  CrashHandler.getInstance();
        crashHandler.init(getApplicationContext(),this);

        /**
         * 为了节省流量、内存等资源，建议初始化的时候对上报进程进行控制，只在主进程下上报数据：
         * 判断是否是主进程（通过进程名是否为包名来判断），并在初始化Bugly时增加一个上报进程的策略配置。
         */
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        //是否是debug状态  调试的时候可以设置为ture  发布则改为false
        Bugly.init(context, Config.APPID, false, strategy);
        // 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
        // CrashReport.initCrashReport(context, strategy);

        //初始化本地自定义配置ip信息
        initCustomIpConfig(context,Config.CUSTOM_IP_CONFIG,Config.CUSTOM_IP_CONFIG,"");

    }

    //初始化本地自定义配置ip信息
    public void initCustomIpConfig(Context context,String fileName,String key,Object defaultObject){
        String  ip = (String) SharePreferencesUtils.get(context,fileName,key,defaultObject);
        if (!TextUtils.isEmpty(ip)){
            Config.initUrl(ip);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;//去除方法数限制
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    

    /**
     * 添加Activity到栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（栈中最后一个压入的）
     *
     * @return
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }


    /**
     * 结束除了当前的其他所有Activity
     *
     * @param activity
     */
    public void killOthersActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i) && activity != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
        activityStack.add(activity);
    }

    /**
     * 结束所有的Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 判断Activity是否存在
     *
     * @param className
     * @return
     */
    public boolean existActivity(String className) {
        Activity activity = getActivityByName(className);
        if (activity != null && !activity.isFinishing()) {
            return true;
        }
        return false;
    }
    
    /**
     * 根据名字查找Activity
     *
     * @param className
     * @return
     */
    public Activity getActivityByName(String className) {
        Activity activity = null;
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                if (activityStack.get(i).getClass().getName().equals(className)) {
                    activity = activityStack.get(i);
                }
            }
        }
        return activity;
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
