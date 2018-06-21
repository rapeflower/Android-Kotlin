package com.lily.kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author lily
 * @date 2018-06-21 16:41
 * @describe 学习Kotlin
 */
class MainActivity : AppCompatActivity() {

    //用val定义常量（相当于final）
    val TAG:String = MainActivity::class.java.simpleName

    //Kotlin提供了懒加载lazy机制：初始化和延迟加载
    private val myTextView:TextView by lazy {
        findViewById(R.id.tvText) as TextView
    }

    //lateinit
    lateinit var clientName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvText.text = "Kotlin demo study"
        myTextView.text = "Kotlin提供了懒加载lazy机制"

        var intent = Intent(this, MainActivity::class.java)
        var list = ArrayList<String>()

        println("当前类名：" + TAG)
    }

    /**
     * 函数：参数id为Int类型
     */
    fun make(id:Int) {
        //变量name为String类型
        var name:String = "my name"
    }

    /**
     * 函数：参数id为Int类型，返回值为String类型
     */
    fun getAddress(id:Int):String {
        return "Address"
    }

    /**
     * 给lateinit变量赋值
     */
    fun initClient(name:String) {
        clientName = name
    }

    /**
     * 空指针安全
     */
    fun jc() {
        var num:Int? = null
        //不做处理返回 null
        val v1 = num?.toInt()
        //判断为空时返回0
        val v2 = num?.toInt() ?:0
        //抛出空指针异常（用“!!”表示不能为空）
        val v3 = num!!.toInt()
    }

    /**
     * 函数返回类型推断
     */
    fun getAddress(id:Int, name:String) = {
        "get address"
    }

    /**
     * 省略'{}'
     */
    fun getAd(id:Int, name:String) = "get address"

    /**
     * 函数也允许空指针安全，在返回类型后面增加“?”即可
     */
    fun ad(id:Int, name:String):String? = "address"

    /**
     * 函数的返回类型是个Unit，表示没有返回
     */
    fun add(id: Int, name: String):Unit {
        //相当于java的void
    }

    /**
     * 在函数无返回时，一般不写Unit
     */
    fun addAddress(id:Int, name:String) {
        //相当于java的void
    }

    /**
     * 用is代替instance of
     */
    fun th(obj:String) {
        if (obj is String) {}
    }

    /**
     * in、区间和集合
     */
    fun test() {
        //用in判断数字是否在某个区间
        var x = 2
        //检查x数值是否在1到5区间
        if (x in 1..5) {}

        //用in判断集合中是否存在某个元素
        var name = "李三"
        var list = ArrayList<String>()
        list.add("张三")
        list.add("莉丝")
        list.add("赵六")
        if (name in list) {}

        //用in遍历整个集合
        for (i in 1..5) {
            println(i)
        }
        for (item in list) {
            println(item)
        }
    }
}
