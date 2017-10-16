package com.zwp.myappframework.sample.activity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.yanzhenjie.permission.AndPermission;
import com.zwp.myappframework.R;
import com.zwp.myappframework.framwork.base.ToolBarActivity;
import com.zwp.myappframework.framwork.http.RequestCallback;
import com.zwp.myappframework.sample.http.AppClient;
import com.zwp.myappframework.sample.model.WeatherResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends ToolBarActivity {

    public static final int REQ_PERMISSION_WRITE_AND_READ = 100;
    @BindView(R.id.tv_text)
    TextView tvText;
    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 先判断是否有权限。
        if(AndPermission.hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // 有权限，直接do anything.
        } else {
            // 申请权限。
            AndPermission.with(this)
                    .requestCode(REQ_PERMISSION_WRITE_AND_READ)
                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                    .send();
        }

//        testInit();
        initData();

    }

//    //解析二维码图片,返回结果封装在Result对象中
//    private com.google.zxing.Result  parseQRcodeBitmap(String bitmapPath){
//        //解析转换类型UTF-8
//        Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
//        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
//        //获取到待解析的图片
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        //如果我们把inJustDecodeBounds设为true，那么BitmapFactory.decodeFile(String path, Options opt)
//        //并不会真的返回一个Bitmap给你，它仅仅会把它的宽，高取回来给你
//        options.inJustDecodeBounds = true;
//        //此时的bitmap是null，这段代码之后，options.outWidth 和 options.outHeight就是我们想要的宽和高了
//        Bitmap bitmap = BitmapFactory.decodeFile(bitmapPath,options);
//        //我们现在想取出来的图片的边长（二维码图片是正方形的）设置为400像素
//        /**
//         options.outHeight = 400;
//         options.outWidth = 400;
//         options.inJustDecodeBounds = false;
//         bitmap = BitmapFactory.decodeFile(bitmapPath, options);
//         */
//        //以上这种做法，虽然把bitmap限定到了我们要的大小，但是并没有节约内存，如果要节约内存，我们还需要使用inSimpleSize这个属性
//        options.inSampleSize = options.outHeight / 400;
//        if(options.inSampleSize <= 0){
//            options.inSampleSize = 1; //防止其值小于或等于0
//        }
//        /**
//         * 辅助节约内存设置
//         *
//         * options.inPreferredConfig = Bitmap.Config.ARGB_4444;    // 默认是Bitmap.Config.ARGB_8888
//         * options.inPurgeable = true;
//         * options.inInputShareable = true;
//         */
//        options.inJustDecodeBounds = false;
//        bitmap = BitmapFactory.decodeFile(bitmapPath, options);
//        //新建一个RGBLuminanceSource对象，将bitmap图片传给此对象
//        RGBLuminanceSource rgbLuminanceSource = new RGBLuminanceSource(bitmap);
//        //将图片转换成二进制图片
//        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(rgbLuminanceSource));
//        //初始化解析对象
//        QRCodeReader reader = new QRCodeReader();
//        //开始解析
//        Result result = null;
//        try {
//            result = reader.decode(binaryBitmap, hints);
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//
//        return result;
//    }



//    private void initData() {
//        RequestCallback<WeatherResult> subscriber = new RequestCallback<WeatherResult>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(WeatherResult weatherResult) {
//                tvText.setText(weatherResult.getSk().toString());
//            }
//        };
//        AppClient.getInstance().getWeatherData(subscriber);
//    }
    private void initData() {
        RequestCallback<WeatherResult> subscriber = new RequestCallback<WeatherResult>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onException(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(WeatherResult weatherResult) {
                tvText.setText(weatherResult.getSk().toString());
            }

        };
        AppClient.getInstance().getWeatherData(subscriber);
    }

    @Override
    public void getPermissionSucceed(int requestCode, List<String> grantedPermissions) {
        super.getPermissionSucceed(requestCode, grantedPermissions);
        Log.e("ccc","main getPermissionSucceed");
    }

    @Override
    public void getPermissionFail(int requestCode, List<String> deniedPermissions) {
        super.getPermissionFail(requestCode, deniedPermissions);
        Log.e("ccc","main getPermissionFail");
    }

    private void testInit() {
//        //发射端
//        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
//                //添加要发送的字段
//                emitter.onNext("我是第一条");
//                emitter.onNext("我是第二条");
//                emitter.onNext("我是第三条");
//                emitter.onNext("我是第四条");
//                emitter.onNext("我是第五条");
//                emitter.onComplete();//发送端可以发结束也可以不发结束
//            }
//        });
//
//        //感应器
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.d(TAG, "subscribe");
//            }
//
//            @Override
//            public void onNext(@NonNull String s) {
//                Log.d(TAG, "" + s);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.d(TAG, "error");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "complete");
//            }
//        };
//
//        //建立连接
//        observable.subscribe(observer);
    }
}
