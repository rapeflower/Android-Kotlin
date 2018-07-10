package com.lily.kotlin.example

/**
 * Created by lilei on 2018/7/10.
 */
class C : A(), B {

    override fun test1() {
        super<A>.test1()
        super<B>.test1()
    }

    override fun test2() {
        super<A>.test2()
        super<B>.test2()
    }
}