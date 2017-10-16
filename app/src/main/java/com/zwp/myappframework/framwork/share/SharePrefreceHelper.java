package com.zwp.myappframework.framwork.share;

import android.content.Context;

/**
 * Created by zwp on 2017/5/23.
 * Description:
 */
public class SharePrefreceHelper extends PrefrenceWrapper {

    private static SharePrefreceHelper sharePrefreceHelper;

    private SharePrefreceHelper(Context context) {
        super(context);
    }

    public static SharePrefreceHelper getInstence(Context context) {
        if (sharePrefreceHelper == null)
            sharePrefreceHelper = new SharePrefreceHelper(context);
        return sharePrefreceHelper;
    }
    public void setIsFirst(boolean isFirst){
        setBoolean("isFirst",isFirst);
    }

    public boolean isFirst(){
        return getBoolean("isFirst",true);
    }
}
