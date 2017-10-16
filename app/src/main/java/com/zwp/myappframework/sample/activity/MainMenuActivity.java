package com.zwp.myappframework.sample.activity;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.lhh.apst.library.Margins;
import com.nineoldandroids.view.ViewHelper;
import com.zwp.myappframework.R;
import com.zwp.myappframework.sample.fragment.FirstFragment;
import com.zwp.myappframework.sample.fragment.FourthFragment;
import com.zwp.myappframework.sample.fragment.LeftFragment;
import com.zwp.myappframework.sample.fragment.RightFragment;
import com.zwp.myappframework.sample.fragment.SecondFragment;
import com.zwp.myappframework.sample.fragment.ThirdFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenuActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    private static final int PAGE_COUNT = 4;
    @BindView(R.id.vp_main)
    ViewPager mViewPager;
    @BindView(R.id.tab_main)
    AdvancedPagerSlidingTabStrip tabMain;
    @BindView(R.id.ll_activity_content)
    LinearLayout llActivityContent;
    @BindView(R.id.activity_main_menu)
    DrawerLayout mDrawerLayout;

    public LeftFragment leftFragment;
    public RightFragment rightFragment;
    public FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        //初始化
        mViewPager.setAdapter(fragmentAdapter);
        mViewPager.setOffscreenPageLimit(PAGE_COUNT);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(this);
        tabMain.setViewPager(mViewPager);

//        leftFragment = new LeftFragment();
//        rightFragment = new RightFragment();
//        FragmentManager lfg = getSupportFragmentManager();//获得事务管理器
//        FragmentManager rfg = getSupportFragmentManager();//获得事务管理器
//        FragmentTransaction lft = lfg.beginTransaction();//开启左侧fragment事务
//        FragmentTransaction rft = rfg.beginTransaction();//开启右侧fragment事务
//        lft.add(R.id.left_fragment,leftFragment);//添加左侧fragment
//        lft.commit();//提交左侧fragment事务
//        rft.add(R.id.right_fragment,rightFragment);//添加右侧fragment
//        rft.commit();//提交右侧fragment事务

        //设置bujubos布局模式  需要关闭手势滑动，从最外侧滑动来滑出弹出框
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        // 设置抽屉阴影
        // mDrawerLayout.setDrawerShadow(R.drawable.ic_launcher, Gravity.LEFT);
        //设置抽屉空余处颜色
        mDrawerLayout.setScrimColor(0x00000000);//设置滑出菜单无覆盖

        //toolbar隐藏返回键
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle(getResources().getString(R.string.main_msg));
//            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        }


        initEvents();//设置侧滑动画

    }


    /**
     * 设置侧滑动画
     */
    private void initEvents() {
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)//侧滑菜单逐渐显示时slideOffset逐渐变大（0~1）
            {
                View mContent = mDrawerLayout.getChildAt(0);//主界面
                View mMenu = drawerView;//侧滑界面
                float scale = 1 - slideOffset;//（1~0）
                float rightScale = 0.8f + scale * 0.2f;//（1~0.8）
                float leftScale = 1 - 0.3f * scale;//（0.7~1）
                if (drawerView.getTag().equals("LEFT")) {
                    //设置滑出菜单x的缩放比例
                    ViewHelper.setScaleX(mMenu, leftScale);//（0.7~1）
                    //设置滑出菜单y的缩放比例
                    ViewHelper.setScaleY(mMenu, leftScale);//（0.7~1）
                    //设置滑出菜单的透明度
                    ViewHelper.setAlpha(mMenu, 0.5f + 0.5f * (1 - scale));//透明度逐渐变大（0.5~1.0）
                    //设置主菜单的透明度
                    ViewHelper.setAlpha(mContent, 1 - 0.5f * slideOffset);//透明度逐渐变小（1.0~0.5）
                    //设置主菜单x向的平移距离
                    ViewHelper.setTranslationX(mContent,//平移X，0~1个侧滑菜单宽度）
                            mMenu.getMeasuredWidth() * (1 - scale));
                    //设置主菜单x的缩放比例
//                    ViewHelper.setPivotX(mContent, 0);
                    //设置主菜单y的缩放比例
//                    ViewHelper.setPivotY(mContent,mContent.getMeasuredHeight() / 2);
                    //设置主菜单y的缩放比例
//                    ViewHelper.setScaleX(mContent, rightScale);//（1~0.8）
                    //设置主菜单y的缩放比例
//                    ViewHelper.setScaleY(mContent, rightScale);//（1~0.8）
                    mContent.invalidate();//刷新（UI线程）
                } else//右滑（暂未添加）
                {
                    ViewHelper.setTranslationX(mContent, -mMenu.getMeasuredWidth() * slideOffset);
                    ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }
        });
    }

    //滑动fragment
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //选中fragment
    @Override
    public void onPageSelected(int position) {
        invalidateOptionsMenu();//
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    //fragment适配器
    private class FragmentAdapter extends FragmentStatePagerAdapter implements
            AdvancedPagerSlidingTabStrip.IconTabProvider,//通过将Adapter实现AdvancedPagerSlidingTabStrip.IconTabProvider可以实现对Tab在不同选择状态下的各种设置。
            AdvancedPagerSlidingTabStrip.LayoutProvider,//通过将Adapter实现AdvancedPagerSlidingTabStrip.LayoutProvider可以实现对Tab以及内容icon的Layout设置。
            AdvancedPagerSlidingTabStrip.TipsProvider //通过将Adapter实现AdvancedPagerSlidingTabStrip.TipsProvider可以实现对小圆点的设置。
    {

        //主页消息
        public FirstFragment firstFragment;
        //应用
        public SecondFragment secondFragment;
        //通讯录
        public ThirdFragment thirdFragment;
        //我
        public FourthFragment fourthFragment;

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
            firstFragment = new FirstFragment();
            secondFragment = new SecondFragment();
            thirdFragment = new ThirdFragment();
            fourthFragment = new FourthFragment();
        }

        //设置对应位置的fragment
        @Override
        public Fragment getItem(int position) {
            Fragment item = null;
            switch (position) {
                case 0:
                    item = firstFragment;
                    break;
                case 1:
                    item = secondFragment;
                    break;
                case 2:
                    item = thirdFragment;
                    break;
                case 3:
                    item = fourthFragment;
                    break;
            }
            return item;
        }

        //设置table的标题
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0://消息
                    return getResources().getString(R.string.main_msg);
                case 1://应用
                    return getResources().getString(R.string.main_mod);
                case 2://通讯录
                    return getResources().getString(R.string.main_contact);
                case 3://我的
                    return getResources().getString(R.string.main_me);
                default:
                    break;
            }
            return super.getPageTitle(position);
        }


        //获得总fragment的数量
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        //设置底部table的默认状态下的icon
        @Override
        public Integer getPageIcon(int position) {
            switch (position) {
                case 0://消息
                    return R.mipmap.menu_massage_normal;
                case 1://应用
                    return R.mipmap.menu_work_normal;
                case 2://通讯录
                    return R.mipmap.menu_contact_normal;
                case 3://我的
                    return R.mipmap.menu_me_normal;
                default:
                    break;
            }
            return 0;
        }

        //设置底部table的选中状态下的icon
        @Override
        public Integer getPageSelectIcon(int position) {
            switch (position) {
                case 0://消息
                    return R.mipmap.menu_massage_press;
                case 1://应用
                    return R.mipmap.menu_work_press;
                case 2://通讯录
                    return R.mipmap.menu_contact_press;
                case 3://我的
                    return R.mipmap.menu_me_press;
                default:
                    break;
            }
            return 0;
        }

        //设置table的小图标
        @Override
        public Rect getPageIconBounds(int position) {
            return null;
        }

        //该方法用于设置每个pageTab在整个tabs中的权重。
        @Override
        public float getPageWeight(int position) {
//            switch (position) {
//                case PAGE_MSG:
//                    return 0.92f;
//                case PAGE_MOD:
//                    return 1.0f;
//                case PAGE_CONTACT:
//                    return 1.0f;
//                case PAGE_ME:
//                    return 0.92f;
//                default:
//                    break;
//            }
            return 1.0f;
        }

        //该方法用于设置每个tab的相对位置，如将tab设置为靠左：return new int[]{ RelativeLayout.ALIGN_PARENT_LEFT};。
        @Override
        public int[] getPageRule(int position) {
            switch (position) {
                case 0:
                    return new int[]{
                            RelativeLayout.CENTER_IN_PARENT};
                case 1:
                    return new int[]{
                            RelativeLayout.CENTER_IN_PARENT};
                case 2:
                    return new int[]{
                            RelativeLayout.CENTER_IN_PARENT};
                case 3:
                    return new int[]{
                            RelativeLayout.CENTER_IN_PARENT};
                default:
                    break;
            }
            return new int[0];
        }

        //该方法用于设置每个tab的间距大小，如将tab设置为距离左边距30px：return new Margins(30,0,0,0);。
        @Override
        public Margins getPageMargins(int position) {
            switch (position) {
                case 0:
//                    return new Margins(DensityUtils.dp2px(MainActivity.this, 20.0f), 0, 0, 0);
                    return null;
                case 1:
                    return null;
                case 2:
                    return null;
                case 3:
//                    return new Margins(0, 0, DensityUtils.dp2px(MainActivity.this, 20.0f), 0);
                    return null;
                default:
                    break;
            }
            return null;
        }


        /**
         * 该方法用于设置小圆点的相对位置。
         * 通过将Adapter实现AdvancedPagerSlidingTabStrip.TipsProvider可以实现对小圆点的设置。
         */
        @Override
        public int[] getTipsRule(int position) {
            switch (position) {
                case 0:
                    return new int[]{
                            RelativeLayout.ALIGN_PARENT_RIGHT};
                case 1:
                    return new int[]{
                            RelativeLayout.ALIGN_PARENT_RIGHT};
                case 2:
                    return new int[]{
                            RelativeLayout.ALIGN_PARENT_RIGHT};
                case 3:
                    return new int[]{
                            RelativeLayout.ALIGN_PARENT_RIGHT};
                default:
                    break;
            }
            return new int[0];
        }

        //该方法用于设置小圆点在tab中的间距大小。
        @Override
        public Margins getTipsMargins(int position) {
            return null;
        }

        //该方法用于设置小圆点的背景，默认为红色圆角图。
        @Override
        public Drawable getTipsDrawable(int position) {
            return null;
        }

    }

}
