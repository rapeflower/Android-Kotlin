package com.lily.kotlin

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.lily.kotlin.base.BaseActivity
import com.lily.kotlin.model.Logistics
import com.lily.kotlin.network.RetrofitManager
import com.lily.kotlin.network.RxSchedulers
import com.lily.kotlin.network.SimpleObserver
import io.reactivex.Observable

/**
 * @author lily
 * @date 2018-07-02 17:34
 * @describe 物流信息界面
 */
class LogisticsActivity : BaseActivity() {

    private val TAG:String = LogisticsActivity::class.java.simpleName

    private val lvLogistics:ListView by lazy {
        findViewById<ListView>(R.id.lv_logistics) as ListView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logistics)

        loadLogistics()
    }

    /**
     * 查询快递信息
     */
    private fun loadLogistics() {
        val observable: Observable<Logistics> = RetrofitManager.getApiService().queryLogistics("zhongtong", "474944203605")
        observable.compose(RxSchedulers.compose(this.bindToLifecycle<Logistics>())).subscribe(object : SimpleObserver<Logistics>(this@LogisticsActivity) {
            override fun onSuccess(data: Logistics) {
                Log.w(TAG, "Request by Kotlin code for result = " + data.data[0].context)
            }

            override fun onFailure(msg: String) {

            }
        })
    }
}