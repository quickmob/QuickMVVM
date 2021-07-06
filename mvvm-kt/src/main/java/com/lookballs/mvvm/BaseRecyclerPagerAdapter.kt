package com.lookballs.mvvm

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import androidx.viewpager.widget.PagerAdapter

/**
 * PagerAdapter基类
 */
class BaseRecyclerPagerAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) : PagerAdapter() {
    private val mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder> = adapter

    override fun getCount(): Int {
        return mAdapter.itemCount
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val holder = mAdapter.createViewHolder(container, 0)
        container.addView(holder.itemView)
        mAdapter.onBindViewHolder(holder, position)
        return holder.itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    init {
        mAdapter.registerAdapterDataObserver(object : AdapterDataObserver() {
            override fun onChanged() {
                mAdapter.notifyDataSetChanged()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                mAdapter.notifyDataSetChanged()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                mAdapter.notifyDataSetChanged()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                mAdapter.notifyDataSetChanged()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                mAdapter.notifyDataSetChanged()
            }

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                mAdapter.notifyDataSetChanged()
            }
        })
    }
}