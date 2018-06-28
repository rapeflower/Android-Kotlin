package com.lily.kotlin.network

import android.content.Context
import com.google.gson.Gson
import com.lily.kotlin.exception.ErrorMessage
import com.lily.kotlin.exception.ErrorType
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @Author rape flower
 * @Date 2018-06-28 14:58
 * @Describe Observer
 */
abstract class SimpleObserver<T>(private val context: Context) : Observer<T> {

    override fun onNext(data: T) {
        onSuccess(data)
    }

    override fun onSubscribe(d: Disposable) {
        //do nothing
    }

    override fun onError(e: Throwable) {
        //HttpException
        if (e is HttpException) {
            val errorMessage: ErrorMessage = when(e.code()) {
                ErrorType.INTERNAL_SERVER_ERROR.errorCode -> ErrorType.INTERNAL_SERVER_ERROR.getErrorMessage(context)
                ErrorType.BAD_GATEWAY.errorCode -> ErrorType.BAD_GATEWAY.getErrorMessage(context)
                ErrorType.NOT_FOUND.errorCode -> ErrorType.NOT_FOUND.getErrorMessage(context)
                else -> Gson().fromJson(e.response().errorBody()?.charStream(), ErrorMessage::class.java)
            }
            onFailure(errorMessage.message)
            return
        }

        //网络异常或其它未知错误
        val errorType: ErrorType = when (e) {
            is UnknownHostException -> ErrorType.NETWORK_NOT_CONNECT
            is ConnectException -> ErrorType.NETWORK_NOT_CONNECT
            is SocketTimeoutException -> ErrorType.CONNECTION_TIMEOUT
            else -> ErrorType.UNEXPECTED_ERROR
        }
        onFailure(errorType.getErrorMessage(context).message)
    }

    override fun onComplete() {
        //do nothing
    }

    abstract fun onSuccess(data: T)

    abstract fun onFailure(msg: String)
}