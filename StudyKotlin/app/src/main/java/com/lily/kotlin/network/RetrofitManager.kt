package com.lily.kotlin.network

import com.lily.kotlin.business.ApiService
import com.lily.kotlin.utils.Inspect
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Author rape flower
 * @Date 2018-06-28 10:48
 * @Describe Retrofit管理类
 */
object RetrofitManager {

    private val TIME_OUT: Long = 10
    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var apiService: ApiService

    /**
     * 初始化
     * 1.为OkHttpClient配置参数
     * 2.初始设置Retrofit
     *
     * @param baseUrl set the API base URL.
     */
    fun init(baseUrl: String) {
        /****** OkHttpClient 配置基本参数 ******/
        val okHttpClientBuilder = OkHttpClient.Builder()
        // 设置连接超时时间
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        // 设置写操作超时时间
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        //设置读操作超时时间
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS)
        // 设置重定向
        okHttpClientBuilder.followRedirects(true)
        /****** 添加https支持 ******/
        okHttpClientBuilder.hostnameVerifier {
            hostname, session -> true
        }
        // 信任所有Https，使用OkHttpClient默认的SSLSocketFactory
        // X509TrustManager trustManager = systemDefaultTrustManager();
        // this.sslSocketFactory = systemDefaultSslSocketFactory(trustManager);
        okHttpClient = okHttpClientBuilder.build()

        /****** Retrofit 基本配置 ******/
        retrofit = buildRetrofit(okHttpClient, baseUrl)
    }

    /**
     * 根据条件构建对应的Retrofit
     *
     * @return
     */
    private fun buildRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                // 添加数据解析ConverterFactory
                .addConverterFactory(GsonConverterFactory.create())
                // 添加RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    /**
     * Default global Retrofit instance
     *
     * @return
     */
    fun getRetrofit(): Retrofit {
        Inspect.asserts((retrofit != null), "Please call the RetrofitManager.init() when your application is started.")
        return retrofit;
    }

    /**
     * Get a new Retrofit instance by baseUrl.
     *
     * @return
     */
    fun createRetrofit(baseUrl: String): Retrofit {
        Inspect.asserts((retrofit != null), "Please call the RetrofitManager.init() when your application is started.")
        return buildRetrofit(okHttpClient, baseUrl)
    }

    /**
     * ApiService
     *
     * @return
     */
    fun getApiService(): ApiService {
        if (apiService == null) {
            apiService = getRetrofit().create(ApiService::class.java)
        }
        return apiService
    }
}