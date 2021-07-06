package com.lookballs.mvvm.action

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * 软键盘相关意图
 */
interface KeyboardAction {
    /**
     * 显示软键盘
     */
    fun showKeyboard(view: View?) {
        val manager = view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE)?.let {
            it as InputMethodManager
        }
        manager?.showSoftInput(view, 0)
    }

    /**
     * 隐藏软键盘
     */
    fun hideKeyboard(view: View?) {
        val manager = view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE)?.let {
            it as InputMethodManager
            it.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    /**
     * 切换软键盘
     */
    fun toggleSoftInput(view: View?) {
        val manager = view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE)?.apply {
            this as InputMethodManager
            toggleSoftInput(0, 0)
        }
    }
}