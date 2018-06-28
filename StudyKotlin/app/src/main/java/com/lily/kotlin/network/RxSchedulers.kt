package com.lily.kotlin.network

import com.trello.rxlifecycle2.LifecycleTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @Author rape flower
 * @Date 2018-06-27 16:41
 * @Describe RxJava线程调度
 * IO线程进行请求，在主线程进行回调
 * <p>单例模式</p>
 */
object RxSchedulers {

    /**
     * 构建ObservableTransformer
     */
    fun <T> compose(lifecycle: LifecycleTransformer<T>): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .doOnSubscribe { disposable -> }
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(lifecycle)
        }
    }
}