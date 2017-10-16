package com.zwp.myappframework.framwork.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by wang-dev
 */
public class KeyBoardUtils {

    /**
     * 打开或关闭软键盘
     * @param context
     */
    public static void toggleSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示软键盘
     * @param view
     * @return
     */
    public static boolean showSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 显示软键盘
     * @param activity
     * @return
     */
    public static boolean showSoftInput(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            return imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
        return false;
    }

    /**
     * 关闭软键盘
     * @param view
     * @return
     */
    public static boolean hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 关闭软键盘
     * @param activity
     * @return
     */
    public static boolean hideSoftInput(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            return imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
        return false;
    }

    /**
     * 判断软键盘是否激活
     * @param context
     * @return
     */
    public static boolean isActive(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();
    }

    /**
     * 计算根布局的的底部空隙
     * 判断根布局的可视区域与屏幕底部的差值,如果这个差大于某个值，可以认定软键盘弹起了.
     */
    private boolean isKeyboardShown(Activity activity) {
        //获取屏幕高度
        int screenHeight = ScreenUtils.getScreenHeight(activity);
        //判断窗口可见区域大小
        Rect r = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        //如果屏幕高度和Window可见区域高度差值大于整个屏幕高度的1/3，则表示软键盘显示中，否则软键盘为隐藏状态。
        int heightDifference = screenHeight - (r.bottom - r.top);
        boolean isKeyboardShowing = heightDifference > screenHeight/3;
        return isKeyboardShowing;
    }
}
