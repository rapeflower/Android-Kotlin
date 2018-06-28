package com.lily.kotlin.application

import android.app.Application
import com.lily.kotlin.network.RetrofitManager

/**
 * @Author rape flower
 * @Date 2018-06-28 14:07
 * @Describe
 */
class SkApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitManager.init("http://www.kuaidi100.com/")
    }
}