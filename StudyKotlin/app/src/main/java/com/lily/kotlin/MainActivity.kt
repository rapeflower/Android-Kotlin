package com.lily.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.lily.kotlin.base.BaseActivity
import com.lily.kotlin.model.Logistics
import com.lily.kotlin.network.BaseObserver
import com.lily.kotlin.network.RetrofitManager
import com.lily.kotlin.network.RxSchedulers
import com.lily.kotlin.network.SimpleObserver
import com.lily.kotlin.singleton.java.InnerSingleton
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author lily
 * @date 2018-06-21 16:41
 * @describe 学习Kotlin
 */
class MainActivity : BaseActivity() {

    //用val定义常量（相当于final）
    val TAG:String = MainActivity::class.java.simpleName

    //Kotlin提供了懒加载lazy机制：初始化和延迟加载
    private val myTextView:TextView by lazy {
        findViewById<TextView>(R.id.tvText) as TextView
    }

    private val btnKotlin: Button by lazy {
        findViewById<Button>(R.id.bt_kotlin) as Button
    }

    //lateinit
    lateinit var clientName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvText.text = "Kotlin demo study"
        myTextView.text = "Kotlin提供了懒加载lazy机制"

        btnKotlin.setOnClickListener {
            v: View? -> getKd_4()
        }

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
    fun th(obj:Any) {
        if (obj is String) {}
    }

    /**
     * in、区间和集合
     */
    fun test() {
        //用in判断数字是否在某个区间
        val x = 2
        //检查x数值是否在1到5区间
        if (x in 1..5) {}

        //用in判断集合中是否存在某个元素
        val name = "李三"
        val list = ArrayList<String>()
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
        //倒序遍历
        for (i in list.size downTo 0) {}
        //反转列表
        for (i in (1..5).reversed()) {}
        //foreach函数
        list.forEach {  }
    }

    /**
     * 用when取代了switch
     * <p>
     * Any表示未知类型
     * <li>
     * 代码中的参数类型Any，相当于Java中的Object，是Kotlin中所有类的基类
     */
    fun doWhen(obj:Any) {
        when(obj) {
            1 -> "is Int"
            in 2..5 -> "between 2-5"
            "str" -> initClient(obj.toString())
            is String -> "is a string"
            else -> "as default" //用else代替switch里面的default
        }
    }

    /**
     * 数据类
     * <p>
     *     数据模型里经常需要一些静态属性或方法，Kotlin可以在数据类里添加一个companion object（伴随对象），
     *     让这个类的所有对象共享这个伴随对象（object在Kotlin中用来表示单例，Kotlin用Any来表示所有类的基类）
     */
    data class Client(var id:Int, var name:String, var birth:String, var address:String)
    data class User(var id:Int, var name:String, var birth:String, var address:String) {
        /**
         * 伴生对象（Companion Object）
         * 每个类都可以实现一个伴生对象，它是该类的所有实例共有的对象。
         * 它将类似于Java中的静态字段。
         */
        companion object {
            private val group = "user-group-id"
        }
    }

    /**
     * 单例模式
     * <p>
     *     单例是很常见的一种设计模式，Kotlin干脆从语言级别提供单例，
     *     关键字为object，如果你在扩展了Kotlin的IDE里输入singleton，
     *     IDE也会自动帮你生成一个伴随对象，也就是一个单例
     */
    object One {
        val group = "user-group-id"
    }

    fun Activity.showToast(msg:String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * 构造函数
     */
    class ClientInfo(id:Int, name:String, address:String)
    class UserInfo constructor(id:Int, name:String, address:String) {//主构造函数
        //二级构造函数必须代理主构造函数
        constructor(id:Int, name:String, birth:String, address:String) : this(id, name, address) {}
        constructor(id:Int, name:String, birth:String, address:String, mobile:String) : this(id, name, birth, address) {

        }

        //初始化模块init
        init {

        }
    }

    /**
     * 查询快递信息
     */
    fun getKd_4() {
        val observable: Observable<Logistics> = RetrofitManager.getApiService().getLogistics("zhongtong", "474944203605")
        observable.compose(RxSchedulers.compose(this.bindToLifecycle<Logistics>())).subscribe(object : SimpleObserver<Logistics>(this@MainActivity) {
            override fun onSuccess(data: Logistics) {
                Log.w(TAG, "Request by Kotlin code for result = " + data.toString())
            }

            override fun onFailure(msg: String) {

            }
        })
    }
}
