package com.zwp.myappframework.sample.fragment;

import android.view.View;
import android.widget.RelativeLayout;

import com.zwp.myappframework.R;
import com.zwp.myappframework.framwork.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zwp on 2017/5/24.
 */

public class RightFragment extends BaseFragment {

    @BindView(R.id.rl_first)
    RelativeLayout rlFirst;
    @BindView(R.id.rl_second)
    RelativeLayout rlSecond;
    @BindView(R.id.rl_third)
    RelativeLayout rlThird;

    @Override
    protected int getResource() {
        return R.layout.fragment_right;
    }


    @Override
    protected void initView(View rootView) {

    }

    @OnClick({R.id.rl_first, R.id.rl_second, R.id.rl_third})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_first:
                break;
            case R.id.rl_second:
                break;
            case R.id.rl_third:
                break;
        }
    }
}
