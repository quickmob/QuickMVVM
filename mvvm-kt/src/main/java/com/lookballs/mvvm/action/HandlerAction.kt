package com.lookballs.mvvm.action

import android.os.Handler
import android.os.Looper
import android.os.SystemClock

/**
 * Handler意图处理
 */
interface HandlerAction {
    /**
     * 获取 Handler
     */
    val handler: Handler
        get() = HANDLER

    /**
     * 延迟执行
     */
    fun post(r: Runnable): Boolean {
        return postDelayed(r, 0)
    }

    /**
     * 延迟一段时间执行
     */
    fun postDelayed(r: Runnable, delayMillis: Long): Boolean {
        var delayMillis = delayMillis
        if (delayMillis < 0) {
            delayMillis = 0
        }
        return postAtTime(r, SystemClock.uptimeMillis() + delayMillis)
    }

    /**
     * 在指定的时间执行
     */
    fun postAtTime(r: Runnable, uptimeMillis: Long): Boolean {
        // 发送和当前对象相关的消息回调
        return HANDLER.postAtTime(r!!, this, uptimeMillis)
    }

    /**
     * 移除单个消息回调
     */
    fun removeCallbacks(r: Runnable) {
        HANDLER.removeCallbacks(r!!)
    }

    /**
     * 移除全部消息回调
     */
    fun removeCallbacks() {
        // 移除和当前对象相关的消息回调
        HANDLER.removeCallbacksAndMessages(this)
    }

    companion object {
        val HANDLER = Handler(Looper.getMainLooper())
    }
}