package com.zwp.myappframework;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zwp on 2017/5/3.
 * 全局异常捕获类
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";

    // CrashHandler 实例
    private static CrashHandler INSTANCE = new CrashHandler();

    // 程序的 Context 对象
    private Context mContext;
    // app对象
    private MyApplication app;

    // 系统默认的 UncaughtException 处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    /**
     * 保证只有一个 CrashHandler 实例
     */
    private CrashHandler() {
    }

    /**
     * 获取 CrashHandler 实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * @param context
     * @param app     传入的app
     * @throws
     * @Title: init
     * @Description: 初始化
     */
    public void init(Context context, MyApplication app) {
        // 传入app对象，为完美终止app
        this.app = app;
        mContext = context;
        // 获取系统默认的 UncaughtException 处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该 CrashHandler 为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当 UncaughtException 发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {//自己处理异常
            mDefaultHandler.uncaughtException(thread, ex);
//            if (ex.getMessage().equals("1234")) {
//                return;
//            }
////            //关闭异常捕获留存服务
////            mContext.stopService(new Intent(mContext, CrashService.class));
////            toastSorry();
//            //一秒后重启app
//            restart();
//
//            //退出程序
//            app.appExit();
////            Activity activity = MyApplication.getInstance().currentActivity();
//            // 当app退出的时候，会执行onTerminate方法，但是有时候不会主动执行。那么强制执行这个方法，让app完美的终止。
//
//            app.onTerminate();
//            // 退出程序
//            //killapp进程
//            android.os.Process.killProcess(android.os.Process.myPid());
//            //完全退出
//            System.exit(1);

        }
    }

//    /**
//     * 弹出异常提示
//     */
//    private void toastSorry() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                Toast.makeText(mContext, "由于发生了未知错误，程序即将关闭，敬请谅解！",
//                        Toast.LENGTH_SHORT).show();
////                DialogUtils.showToastCenter("askjflasfsfasdf");
//                Looper.loop();
//            }
//        }).start();
//    }

    /* 重启应用 */
    private void restart() {
//        Intent intent = new Intent(app.getApplicationContext(), LoginActivity.class);
//        PendingIntent restartIntent = PendingIntent.getActivity(app.getApplicationContext(), 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
//        //退出程序
//        AlarmManager mgr = (AlarmManager) app.getSystemService(Context.ALARM_SERVICE);
//        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent); // 1秒钟后重启应用
    }

    /**
     * 自定义错误处理，收集错误信息，发送错误报告等操作均在此完成
     *
     * @param ex
     * @return true：如果处理了该异常信息；否则返回 false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
//         //收集设备参数信息
//        collectDeviceInfo(mContext);
//        // 保存日志文件
//        saveCrashInfo2File(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);

            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();

        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "error-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/crashs";
//                Toast.makeText(mContext, path, Toast.LENGTH_LONG).show();
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.flush();
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }

        return null;
    }
}
