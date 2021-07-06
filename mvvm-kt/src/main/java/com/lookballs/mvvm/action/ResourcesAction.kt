package com.lookballs.mvvm.action

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat

/**
 * Context 意图处理（扩展非 Context 类的方法，请不要用 Context 子类实现此接口）
 */
interface ResourcesAction {
    val `$context`: Context

    val `$resources`: Resources
        get() = `$context`.resources

    fun `$getString`(@StringRes id: Int): String? {
        return `$context`.getString(id)
    }

    fun `$getString`(@StringRes id: Int, vararg formatArgs: Any?): String? {
        return `$resources`.getString(id, *formatArgs)
    }

    fun `$getDimension`(@DimenRes id: Int): Float {
        return `$resources`.getDimension(id)
    }

    fun `$getDimensionPixelSize`(@DimenRes id: Int): Int {
        return `$resources`.getDimensionPixelSize(id)
    }

    fun `$getDrawable`(@DrawableRes id: Int): Drawable? {
        return ContextCompat.getDrawable(`$context`, id)
    }

    @ColorInt
    fun `$getColor`(@ColorRes id: Int): Int {
        return ContextCompat.getColor(`$context`, id)
    }

    fun `$getColorStateList`(@ColorRes id: Int): ColorStateList? {
        return ContextCompat.getColorStateList(`$context`, id)
    }

    fun <S> `$getSystemService`(serviceClass: Class<S>): S? {
        return ContextCompat.getSystemService(`$context`, serviceClass)
    }
}