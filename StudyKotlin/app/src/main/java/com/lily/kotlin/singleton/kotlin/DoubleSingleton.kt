package com.lily.kotlin.singleton.kotlin

/**
 * @Author rape flower
 * @Date 2018-06-29 13:44
 * @Describe 单例模式：Kotlin方式
 * <p>双重检测式：判断空、加锁</p>
 * Kotlin方式的有两种写法：
 * 第一种是Kotlin原生的通过lazy和mode = LazyThreadSafetyMode.SYNCHRONIZED（就是锁的意思）实现单例
 * 第二种是用Kotlin语法写的Java方式双重检测式
 */
class DoubleSingleton private constructor() {

    companion object {
        /**
         * 第一种：Kotlin原生写法
         */
        val instance_native by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DoubleSingleton()
        }

        /**
         * 第二种：用Kotlin语法写的Java方式双重检测式
         */
        @Volatile
        private var kInstance: DoubleSingleton? = null

        fun getInstance(): DoubleSingleton {
            if (kInstance == null) {
                synchronized(DoubleSingleton::class) {
                    if (kInstance == null) {
                        kInstance = DoubleSingleton()
                    }
                }
            }
            return kInstance!!
        }
    }
}