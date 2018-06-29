package com.lily.kotlin.singleton.kotlin

/**
 * @Author rape flower
 * @Date 2018-06-29 14:29
 * @Describe 单例模式：Kotlin方式
 * <p>内部类式</p>
 */
class InnerSingleton private constructor() {

    private object Holder {
        val INSTANCE = InnerSingleton()
    }

    companion object {
        fun getInstance(): InnerSingleton {
            return Holder.INSTANCE
        }
    }
}