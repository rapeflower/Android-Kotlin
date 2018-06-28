package com.lily.kotlin.network

/**
 * Created by lilei on 2018/6/28.
 */
data class Response<T>(var resultCode: Int, var result: T, var message: String)