package com.lily.kotlin.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * @author lily
 * @date 2018-07-02 11:06
 * @describe 通用数据适配器
 */
abstract class AbstractBaseAdapter<T>(private val context: Context?, private val data: List<T>?) : BaseAdapter() {

    private var mInflater: LayoutInflater? = null

    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var holder: AbstractViewHolder
        var view: View
        if (convertView == null) {
            view = mInflater?.inflate(onBindLayout(), parent, false) ?: View(context)
            holder = onCreateViewHolder()
            if (holder == null) {
                throw NullPointerException("The view holder object is null, you must instance a view holder.")
            }
            holder.initWidget(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as AbstractViewHolder
        }
        onBindViewHolder(holder, getItem(position), position)
        return view
    }

    override fun getItem(position: Int): T {
        return data!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data!!.size
    }

    /**
     * 绑定布局文件
     * 传入的是布局文件的ID
     *
     * @return 布局文件的资源ID（ex: R.layout.xxx）
     */
    abstract fun onBindLayout(): Int

    /**
     * 将数据一一对应绑定到视图（View）
     *
     * @param holder 缓存View的容器
     * @param data 数据
     * @param position 索引
     */
    abstract fun onBindViewHolder(holder: AbstractViewHolder, data: T, position: Int)

    /**
     * 创建View缓存容器
     *
     * @return 缓存View的容器
     */
    abstract fun onCreateViewHolder(): AbstractViewHolder
}