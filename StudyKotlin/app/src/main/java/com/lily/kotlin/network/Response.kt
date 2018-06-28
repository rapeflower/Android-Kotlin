package com.lily.kotlin.network

/**
 * @Author rape flower
 * @Date 2018-06-28 18:17
 * @Describe 响应数据model
 */
data class Response<T>(var resultCode: Int, var result: T, var message: String)