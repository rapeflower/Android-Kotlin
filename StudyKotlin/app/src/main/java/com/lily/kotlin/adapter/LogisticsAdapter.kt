package com.lily.kotlin.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.lily.kotlin.base.AbstractBaseAdapter
import com.lily.kotlin.base.AbstractViewHolder
import com.lily.kotlin.model.Logistics
import com.lily.kotlin.R

/**
 * @author lily
 * @date 2018-07-02 10:57
 * @describe 物流信息适配器
 */
class LogisticsAdapter(context: Context?, list: MutableList<Logistics.Details>?) : AbstractBaseAdapter<Logistics.Details>(context, list) {

    override fun onBindLayout(): Int {
        return R.layout.logistics_item
    }

    override fun onBindViewHolder(holder: AbstractViewHolder, data: Logistics.Details, position: Int) {
        val lHolder = holder as LogisticsHolder
        lHolder.tvTime.text = data.time
        lHolder.tvContext.text = data.context
        lHolder.tvFtime.text = data.ftime
    }

    override fun onCreateViewHolder(): AbstractViewHolder {
        return LogisticsHolder()
    }

    class LogisticsHolder : AbstractViewHolder() {
        lateinit var tvTime: TextView
        lateinit var tvContext: TextView
        lateinit var tvFtime: TextView

        override fun initWidget(view: View) {
            tvTime = view.findViewById(R.id.tv_time)
            tvContext = view.findViewById(R.id.tv_context)
            tvFtime = view.findViewById(R.id.tv_ftime)
        }
    }
}