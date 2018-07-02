package com.lily.kotlin.adapter

import android.content.Context
import android.view.View
import com.lily.kotlin.base.AbstractBaseAdapter
import com.lily.kotlin.base.AbstractViewHolder
import com.lily.kotlin.model.Logistics
import com.lily.kotlin.R

/**
 * @author lily
 * @date 2018-07-02 10:57
 * @describe 物流信息适配器
 */
class LogisticsAdapter(context: Context?, list: List<Logistics>?) : AbstractBaseAdapter<Logistics>(context, list) {

    override fun onBindLayout(): Int {
        return R.layout.logistics_item
    }

    override fun onBindViewHolder(holder: AbstractViewHolder, data: Logistics, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(): AbstractViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder : AbstractViewHolder() {

        override fun initWidget(view: View) {

        }
    }
}