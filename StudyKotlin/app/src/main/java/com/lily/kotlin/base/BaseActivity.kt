package com.lily.kotlin.base

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxFragmentActivity

/**
 * @author lily
 * @date 2018-06-27 16:06
 * @describe
 */
open class BaseActivity : RxFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}