package com.lily.kotlin.ui

import android.os.Build
import android.support.annotation.IntRange
import android.view.View
import android.view.View.*
import com.lily.kotlin.internal.ViewHelper

/**
 * 隐藏 View
 */
fun View.hide(isTrue: Boolean = true) {
    visibility = if (isTrue) GONE else VISIBLE
}

/**
 * 显示/隐藏 View
 * @param isTrue {@code true} 显示
 *               {@code false} 隐藏
 */
fun View.show(isTrue: Boolean = true) {
    visibility = if (isTrue) VISIBLE else GONE
}

/**
 * [View]点击事件，点击[doActionAfterTimes]次后执行[action]，如果[doActionAfterTimes]==1，
 * [millisecondInterval]是防止重复点击的间隔；如果[doActionAfterTimes]>1，相当于双击，多次点击，
 * 以[millisecondInterval]内点击记录点击次数，点击次数到[doActionAfterTimes]，触发[action]，
 *
 * [doActionAfterTimes]默认为1 [millisecondInterval]默认500ms
 */
fun View.onClick(@IntRange(from = 1, to = Long.MAX_VALUE) doActionAfterTimes: Int = 1,
                 @IntRange(from = 1, to = Long.MAX_VALUE) millisecondInterval: Int = 500,
                 action: () -> Unit) {
    ViewHelper.onClick(this, doActionAfterTimes, millisecondInterval, action)
}

/**
 * 获取[View] id，如果没有id：SDK > 17，使用[View.generateViewId]；否侧使用[View.hashCode]
 */
fun View.getViewId(): Int {
    var id = id;
    if (id == NO_ID) {
        id = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            generateViewId()
        } else {
            this.hashCode()
        }
    }

    return id
}