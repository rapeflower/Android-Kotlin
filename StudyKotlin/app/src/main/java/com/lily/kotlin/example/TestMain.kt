package com.lily.kotlin.example

/**
 * Created by lilei on 2018/7/11.
 */
class TestMain {

    fun main(args: Array<String>) {
        println("test main")

        val str = "kotlin very good"
        str.first()
        str[0]
        str.get(0)
        // first{}高阶函数
        str.first { it == 'd' }
        str.length
        str.count()
        str.isBlank()
        str.plus(", quickly study")


        // 高级的二元操作
        var arrA = arrayListOf<String>("1", "2", "3", "4", "5")
        var arrB = arrayListOf<String>("6", "7", "8", "9", "10")

        arrA.plusAssign(arrB)
        for (a in arrA) {
            print("$a\t")
        }
    }
}