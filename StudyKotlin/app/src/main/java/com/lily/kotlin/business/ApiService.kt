package com.lily.kotlin.business

import com.lily.kotlin.model.JLogistics
import com.lily.kotlin.model.Logistics
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @Author rape flower
 * @Date 2018-06-28 18:05
 * @Describe
 */
interface ApiService {

    /**
     * 查询快递数据
     * @param type
     * @param postid
     * @return
     */
    @FormUrlEncoded
    @POST("query")
    fun getLogistics(@Field("type") type: String, @Field("postid") postid: String): Observable<JLogistics>

    /**
     * 查询物流信息
     * @param type
     * @param postid
     * @return
     */
    @FormUrlEncoded
    @POST("query")
    fun queryLogistics(@Field("type") type: String, @Field("postid") postid: String): Observable<Logistics>
}