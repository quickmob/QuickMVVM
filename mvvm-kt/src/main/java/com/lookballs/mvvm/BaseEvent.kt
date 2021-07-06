package com.lookballs.mvvm

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：EventBus事件基类
 */
class BaseEvent<T> {
    var code = 0
    var data: T? = null

    @JvmOverloads
    constructor(code: Int, data: T? = null) {
        this.code = code
        this.data = data
    }
}