package com.zwp.myappframework.framwork.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zwp.myappframework.R;
import com.zwp.myappframework.framwork.utils.KeyBoardUtils;


/**
 * Created by zwp on 2017/5/23.
 */

public class ToolBarActivity extends BaseActivity {

    //基础布局
    protected LinearLayout rootLayout;

    //工具栏
    protected Toolbar toolbar;
    private TextView titleView;//标题

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_toolbar);//设置父类content布局

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.llyt_root);
        if (rootLayout == null) return;
        //把子类布局加载到父类布局中
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //初始化toolbar
        initToolbar();
    }

    /**
     * 初始化toolbar
     */
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        if (toolbar != null) {
            this.toolbar=toolbar;
            this.titleView = textView;
            setSupportActionBar(toolbar);
        }
    }

    /**
     * 设置自定义标题
     * @param title
     */
    public void setInTitle(CharSequence title){
        //隐藏自带的标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //设置自己的标题
        titleView.setText(title);
//        titleView.setTextColor(getResources().getColor(R.color.text_color_black_info_2));
        //设置toolbar的背景颜色
//        toolbar.setBackgroundColor(getResources().getColor(R.color.bg_title_blue));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//隐藏返回箭头
    }

    /**
     * 获取标题控件
     * @return
     */
    public TextView getInTitleView(){
        if (titleView == null){
            return titleView = (TextView) findViewById(R.id.toolbar_title);
        }
        return titleView;
    }

    @Override
    protected void onStop() {
        //关闭软键盘
        KeyBoardUtils.hideSoftInput(this);
        super.onStop();
    }
}
