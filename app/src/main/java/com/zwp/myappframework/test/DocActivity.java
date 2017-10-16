//package com.zwp.myappframework.test;
//
//import android.content.ActivityNotFoundException;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.zwp.myappframework.R;
//
//import java.io.File;
//
//public class DocActivity extends AppCompatActivity {
//
//
//    private Button tv;
//    String stringPath;
//    MyBroadCastReciver receiver;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_doc);
//
//        stringPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/test.docx";
//
//        tv = (Button) findViewById(R.id.btn_dianwo);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean flag = openFile(stringPath);
//                if (flag == true) {
//                    Toast.makeText(DocActivity.this," 打开文件成功", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(DocActivity.this, "打开文件失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    boolean openFile(String path) {
//        Intent intent = new Intent();
//        Bundle bundle = new Bundle();
//        bundle.putString(WpsModel.OPEN_MODE, WpsModel.OpenMode.NORMAL); // 打开模式
//        bundle.putBoolean(WpsModel.SEND_CLOSE_BROAD, true); // 关闭时是否发送广播
//        bundle.putString(WpsModel.THIRD_PACKAGE, getPackageName()); // 第三方应用的包名，用于对改应用合法性的验证
//        bundle.putBoolean(WpsModel.CLEAR_TRACE, true);// 清除打开记录
//        // bundle.putBoolean(CLEAR_FILE, true); //关闭后删除打开文件
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction(android.content.Intent.ACTION_VIEW);
//        intent.setClassName(WpsModel.PackageName.NORMAL, WpsModel.ClassName.NORMAL);
//
//        File file = new File(path);
//        if (file == null || !file.exists()) {
//            Toast.makeText(this, "文件为空或者不存在", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        Uri uri = Uri.fromFile(file);
//        intent.setData(uri);
//        intent.putExtras(bundle);
//        try {
//            startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            Toast.makeText(this, "打开wps异常", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//}
