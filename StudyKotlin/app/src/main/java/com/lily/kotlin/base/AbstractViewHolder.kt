package com.lily.kotlin.base

import android.view.View

/**
 * @author lily
 * @date 2018-07-02 13:53
 * @describe 通用View缓存类（容器），也就是我们常说的'ViewHolder'，
 * 在数据适配器中创建的ViewHolder都需要集成此类。
 */
abstract class AbstractViewHolder {

    /**
     * 初始化View
     * <p>在此方法中通过view.findViewById(R.id.xxx)来实例化View</p>
     * @param view
     */
    abstract fun initWidget(view: View)
}