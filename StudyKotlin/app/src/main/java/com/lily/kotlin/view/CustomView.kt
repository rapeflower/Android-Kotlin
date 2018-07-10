package com.lily.kotlin.view

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * @Author rape flower
 * @Date 2018-07-10 17:47
 * @Describe 自定义View（存在主构造函数）
 */
class CustomView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    constructor(context: Context?) : this(context, null, 0)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
}