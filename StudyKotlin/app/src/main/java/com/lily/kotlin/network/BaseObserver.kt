package com.lily.kotlin.network

import android.content.Context
import com.lily.kotlin.exception.ErrorMessage
import com.lily.kotlin.exception.ErrorType
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @Author rape flower
 * @Date 2018-06-28 14:58
 * @Describe Observer
 */
abstract class BaseObserver<T>(private val context: Context) : Observer<Response<T>> {

    override fun onNext(data: Response<T>) {
        if (data.resultCode == 0) {
            onSuccess(data.result)
        } else {
            val errorMessage: ErrorMessage = when(data.resultCode) {
                ErrorType.INTERNAL_SERVER_ERROR.errorCode -> ErrorType.INTERNAL_SERVER_ERROR.getErrorMessage(context)
                ErrorType.BAD_GATEWAY.errorCode -> ErrorType.BAD_GATEWAY.getErrorMessage(context)
                ErrorType.NOT_FOUND.errorCode -> ErrorType.NOT_FOUND.getErrorMessage(context)
                else -> ErrorMessage(data.resultCode, data.message)
            }
            onFailure(errorMessage.message)
        }
    }

    override fun onSubscribe(d: Disposable) {
        //do nothing
    }

    override fun onError(e: Throwable) {
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