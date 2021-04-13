package com.lily.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.lily.kotlin.base.BaseActivity
import com.lily.kotlin.business.ApiService
import com.lily.kotlin.model.JLogistics
import com.lily.kotlin.model.News
import com.lily.kotlin.model.Repo
import com.lily.kotlin.network.*
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
            v: View? -> goLogistics()
        }

        var intent = Intent(this, MainActivity::class.java)
        var list = ArrayList<String>()

        println("当前类名：" + TAG)
        getRepos()
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
     * 高阶函数
     */
    fun gj() {
        val str = "abcde"
        val sum = str.sumBy { it.toInt() }
        println(sum)

        ld(10, {num1: Int, num2: Int -> num1 + num2 })
    }

    /**
     * 自定义高阶函数
     */
    fun ld(a: Int, b: (num1: Int, num2: Int) -> Int): Int {
        return a + b.invoke(2, 6)
    }

    fun resultByOpt(num1: Int, num2: Int, result: (Int, Int) -> Int) : Int {
        return result(num1, num2)
    }

    /**
     * run 函数
     */
    fun testRun() {
        val str = "mini"

        run {
            //和上面的变量str不会冲突
            val str = "java"
            println("str= $str")
        }

        println("str= $str")

        // T.run()
        val ks = "kotlin"
        ks.run {
            println("length = ${this.length}")
            println("first = ${first()}")
            println("last = ${last()}")
        }
        // let
        ks?.let {
            println(it.length)
            println(it.first())
            println(it.last())
        }

        // with
        with(ks) {
            println("length = ${this.length}")
            println("first = ${first()}")
            println("last = ${last()}")
        }

        val arr = arrayOf(1, 3, 4, 5, 6, 7, 8, 9)

        val mp = mapOf("key1" to "value1", "key2" to "value2", "key3" to "value3")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mp.forEach { key, value -> println("$key \t $value") }
        }

        //在使用Lambda表达式的时候，可以用下划线(_)表示未使用的参数，表示不处理这个参数。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mp.forEach {
                _, value -> println("$value")
            }
        }

        val iop = fun Int.(other: Int) : Int = this + other
        println(2.iop(3))

        val news = News("101010", "/abc/src/img/test.png")
        val (id, img) = news
        println(news)
        val m_news = news.copy(id = "15235793223036")
        println(m_news)
    }

    /**
     * 密封类
     * sealed
     */
    sealed class TestSealed {
        data class Person(val num1: Int, val num2: Int) : TestSealed()

        object Add : TestSealed()

        object Minus : TestSealed()
    }

    // 其子类可以定在密封类外部，但是必须在同一文件中 v1.1之前只能定义在密封类内部
    //object ChildTestSealed : TestSealed()

    /**
     * 查询快递信息
     */
    fun getKd_4() {
        val observable: Observable<JLogistics> = RetrofitManager.getApiService().getLogistics("zhongtong", "474944203605")
        observable.compose(RxSchedulers.compose(this.bindToLifecycle<JLogistics>())).subscribe(object : SimpleObserver<JLogistics>(this@MainActivity) {
            override fun onSuccess(data: JLogistics) {
                Log.w(TAG, "Request by Kotlin code for result = " + data.toString())
            }

            override fun onFailure(msg: String) {

            }
        })
    }

    /**
     * 获取GitHub Repo
     */
    fun getRepos() {
        RetrofitManager.createRetrofit("https://api.github.com/")
                .create(ApiService::class.java)
                .getRepos("rapeflower").compose(RxSchedulers.compose(this.bindToLifecycle<List<Repo>>()))
                .subscribe(object : SimpleObserver<List<Repo>>(this@MainActivity) {
                    override fun onSuccess(data: List<Repo>) {
                        Log.w(TAG, data[0].owner.login + "\n"
                                + data[0].name + "\n"
                                + data[0].description + "\n"
                                + data[0].html_url)
                    }

                    override fun onFailure(msg: String) {
                        Log.w(TAG, "onFailure: " + msg)
                    }
                })
    }

    /**
     * 查看物流信息
     */
    fun goLogistics() {
        var intent = Intent(this, LogisticsActivity::class.java)
        startActivity(intent)
    }
}
