package com.lily.kotlin.view

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * @Author rape flower
 * @Date 2018-07-10 17:41
 * @Describe 自定义View（无主构造函数）
 */
class MyView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr : Int) : super(context, attrs, defStyleAttr)
}