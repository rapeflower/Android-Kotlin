package com.lily.kotlin.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.lily.kotlin.model.News

/**
 * @author lily
 * @date 2018-06-27 10:39
 * @describe
 */
class NewsAdapter(context: Context?, data: List<News>?) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return convertView!!;
    }

    override fun getItem(position: Int): Any {
        return Any();
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    override fun getCount(): Int {
        return 0;
    }
}