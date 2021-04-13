package com.lily.kotlin.internal

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.EditText
import com.lily.kotlin.ui.edittext.BaseTextWatcher
import com.lily.kotlin.ui.getViewId

internal object ViewHelper {
    private val clickMap by lazy { hashMapOf<Int, Triple<Int, Int, Long>>() }
    private val textChangeMap by lazy { hashMapOf<EditText, Pair<BaseTextWatcher, Runnable>>() }

    fun onClick(view: View, doActionAfterTimes: Int = 1, millisecondInterval: Int = 300, action: () -> Unit) {
        view.setOnClickListener {
            val viewId = view.getViewId()
            val t = clickMap[viewId]
            var times = doActionAfterTimes
            if (times < 1) {
                times = 1
            }

            val third = System.currentTimeMillis()
            if (times == 1) {
                if (t == null || (third - t.third >= millisecondInterval)) {
                    action.invoke()
                    clickMap[viewId] = Triple(1, 1, third)
                }
            } else {
                if (t == null) {
                    clickMap[viewId] = Triple(times, 1, third)
                } else {
                    if (third - t.third >= millisecondInterval) {
                        clickMap[viewId] = Triple(times, 1, third)
                    } else {
                        val newT = t.copy(second = t.second + 1, third = third)
                        clickMap[viewId] = newT;
                        if (newT.second != newT.first) {
                            // 不做任何操作
                        } else {
                            action.invoke()
                            clickMap.remove(viewId)
                        }
                    }
                }
            }
        }
    }

    private val textChangeObserver by lazy {
        object: LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroyed() {
                textChangeMap.forEach {
                    val et = it.key
                    val tripe = textChangeMap[et]
                    et.removeCallbacks(tripe?.second)
                    et.removeTextChangedListener(tripe?.first)
                }
                if (textChangeMap.isNotEmpty()) {
                    val et = textChangeMap.keys.first()
                    (et.context as? FragmentActivity)?.lifecycle?.removeObserver(this)
                    textChangeMap.clear()
                }
            }
        }
    }

    fun textChange(et: EditText, timeout: Int, ignoreEmpty: Boolean, action: (text: String) -> Unit) {

    }
}