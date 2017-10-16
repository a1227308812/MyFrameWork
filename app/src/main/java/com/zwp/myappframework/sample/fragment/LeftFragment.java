package com.zwp.myappframework.sample.fragment;

import android.view.View;
import android.widget.RelativeLayout;

import com.zwp.myappframework.R;
import com.zwp.myappframework.framwork.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by zwp on 2017/5/24.
 */

public class LeftFragment extends BaseFragment {

    @BindView(R.id.rl_first)
    RelativeLayout rlFirst;
    @BindView(R.id.rl_second)
    RelativeLayout rlSecond;
    @BindView(R.id.rl_third)
    RelativeLayout rlThird;

    @Override
    protected int getResource() {
        return R.layout.fragment_left;
    }


    @Override
    protected void initView(View rootView) {
        
    }

}
