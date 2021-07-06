package com.lookballs.mvvm

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

/**
 * FragmentPagerAdapter基类
 */
class BaseFragmentPagerAdapter<F : Fragment>(manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    /**
     * Fragment 集合
     */
    private val mFragments: MutableList<F> by lazy {
        mutableListOf<F>()
    }

    /**
     * Fragment 标题
     */
    private val mFragmentTitles: MutableList<CharSequence?> by lazy {
        mutableListOf<CharSequence?>()
    }

    /**
     * 获取当前的Fragment
     */
    /**
     * 当前显示的Fragment
     */
    var showFragment: F? = null
        private set

    /**
     * 当前 ViewPager
     */
    private var mViewPager: ViewPager? = null

    /**
     * 设置成懒加载模式
     */
    private var mLazyMode = true

    constructor(activity: FragmentActivity) : this(activity.supportFragmentManager) {}
    constructor(fragment: Fragment) : this(fragment.childFragmentManager) {}

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).hashCode().toLong()
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitles[position]
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
        if (showFragment !== `object`) {
            // 记录当前的Fragment对象
            showFragment = `object` as F
        }
    }

    /**
     * 添加 Fragment
     */
    @JvmOverloads
    fun addFragment(fragment: F, title: CharSequence? = null) {
        mFragments.add(fragment!!)
        mFragmentTitles.add(title)
        refreshAdapter()
    }

    @JvmOverloads
    fun addFragments(fragments: List<F>, titles: List<CharSequence>? = null) {
        mFragments.clear()
        mFragmentTitles.clear()
        mFragments.addAll(fragments!!)
        titles?.let {
            mFragmentTitles.addAll(it)
        }
        refreshAdapter()
    }

    /**
     * 移除Fragment
     */
    fun removeFragment(index: Int) {
        if (mFragments.size == 0 || index >= mFragments.size) {
            return
        }
        mFragments.removeAt(index)
        mFragmentTitles.removeAt(index)
        refreshAdapter()
    }

    fun removeFragment(fragment: F) {
        var index = -1
        for (i in mFragments.indices) {
            if (mFragments[i] === fragment) {
                index = i
                break
            }
        }
        if (index != -1) {
            removeFragment(index)
        }
    }

    /**
     * 刷新适配器
     */
    private fun refreshAdapter() {
        mViewPager?.apply {
            notifyDataSetChanged()
            offscreenPageLimit = if (mLazyMode) {
                count
            } else {
                1
            }
        }
    }

    /**
     * 获取某个 Fragment 的索引（没有就返回 -1）
     */
    fun getFragmentIndex(clazz: Class<out Fragment>): Int {
        for (i in mFragments.indices) {
            if (clazz.name == mFragments[i].javaClass.name) {
                return i
            }
        }
        return -1
    }

    override fun startUpdate(container: ViewGroup) {
        super.startUpdate(container)
        if (container is ViewPager) {
            // 记录绑定 ViewPager
            mViewPager = container
            refreshLazyMode()
        }
    }

    /**
     * 设置懒加载模式
     */
    fun setLazyMode(lazy: Boolean) {
        mLazyMode = lazy
        refreshLazyMode()
    }

    /**
     * 刷新加载模式
     */
    private fun refreshLazyMode() {
        if (mLazyMode) {
            // 设置成懒加载模式（也就是不限制 Fragment 展示的数量）
            mViewPager?.offscreenPageLimit = count
        } else {
            mViewPager?.offscreenPageLimit = 1
        }
    }
}