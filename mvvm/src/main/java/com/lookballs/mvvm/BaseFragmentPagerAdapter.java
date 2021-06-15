package com.lookballs.mvvm;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * FragmentPagerAdapter基类
 */
public final class BaseFragmentPagerAdapter<F extends Fragment> extends FragmentPagerAdapter {

    /**
     * Fragment 集合
     */
    private final List<F> mFragments = new ArrayList<>();
    /**
     * Fragment 标题
     */
    private final List<CharSequence> mFragmentTitles = new ArrayList<>();
    /**
     * 当前显示的Fragment
     */
    private F mShowFragment;
    /**
     * 当前 ViewPager
     */
    private ViewPager mViewPager;
    /**
     * 设置成懒加载模式
     */
    private boolean mLazyMode = true;

    public BaseFragmentPagerAdapter(@NonNull FragmentActivity activity) {
        this(activity.getSupportFragmentManager());
    }

    public BaseFragmentPagerAdapter(@NonNull Fragment fragment) {
        this(fragment.getChildFragmentManager());
    }

    public BaseFragmentPagerAdapter(@NonNull FragmentManager manager) {
        super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public F getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        if (getShowFragment() != object) {
            // 记录当前的Fragment对象
            mShowFragment = (F) object;
        }
    }

    /**
     * 添加 Fragment
     */
    public void addFragment(@NonNull F fragment) {
        addFragment(fragment, null);
    }

    public void addFragment(@NonNull F fragment, CharSequence title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
        refreshAdapter();
    }

    public void addFragments(List<F> fragments) {
        addFragments(fragments, null);
    }

    public void addFragments(List<F> fragments, List<CharSequence> titles) {
        mFragments.clear();
        mFragmentTitles.clear();
        mFragments.addAll(fragments);
        mFragmentTitles.addAll(titles);
        refreshAdapter();
    }

    /**
     * 移除Fragment
     */
    public void removeFragment(int index) {
        if (mFragments.size() == 0 || index >= mFragments.size()) {
            return;
        }
        mFragments.remove(index);
        mFragmentTitles.remove(index);
        refreshAdapter();
    }

    public void removeFragment(@NonNull F fragment) {
        int index = -1;
        for (int i = 0; i < mFragments.size(); i++) {
            if (mFragments.get(i) == fragment) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            removeFragment(index);
        }
    }

    /**
     * 刷新适配器
     */
    private void refreshAdapter() {
        if (mViewPager != null) {
            notifyDataSetChanged();
            if (mLazyMode) {
                mViewPager.setOffscreenPageLimit(getCount());
            } else {
                mViewPager.setOffscreenPageLimit(1);
            }
        }
    }

    /**
     * 获取当前的Fragment
     */
    public F getShowFragment() {
        return mShowFragment;
    }

    /**
     * 获取某个 Fragment 的索引（没有就返回 -1）
     */
    public int getFragmentIndex(Class<? extends Fragment> clazz) {
        if (clazz != null) {
            for (int i = 0; i < mFragments.size(); i++) {
                if (clazz.getName().equals(mFragments.get(i).getClass().getName())) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void startUpdate(@NonNull ViewGroup container) {
        super.startUpdate(container);
        if (container instanceof ViewPager) {
            // 记录绑定 ViewPager
            mViewPager = (ViewPager) container;
            refreshLazyMode();
        }
    }

    /**
     * 设置懒加载模式
     */
    public void setLazyMode(boolean lazy) {
        mLazyMode = lazy;
        refreshLazyMode();
    }

    /**
     * 刷新加载模式
     */
    private void refreshLazyMode() {
        if (mViewPager == null) {
            return;
        }

        if (mLazyMode) {
            // 设置成懒加载模式（也就是不限制 Fragment 展示的数量）
            mViewPager.setOffscreenPageLimit(getCount());
        } else {
            mViewPager.setOffscreenPageLimit(1);
        }
    }
}