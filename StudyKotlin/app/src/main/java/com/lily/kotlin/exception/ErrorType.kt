package com.lily.kotlin.exception

import android.content.Context
import android.support.annotation.StringRes
import com.lily.kotlin.R

/**
 * @Author rape flower
 * @Date 2018-06-28 15:39
 * @Describe
 */
enum class ErrorType(val errorCode: Int, @param: StringRes private val messageId: Int) {

    INTERNAL_SERVER_ERROR(500, R.string.service_error),
    BAD_GATEWAY(502, R.string.service_error),
    NOT_FOUND(404, R.string.not_found),
    CONNECTION_TIMEOUT(408, R.string.timeout),
    NETWORK_NOT_CONNECT(499, R.string.network_wrong),
    UNEXPECTED_ERROR(1000, R.string.unexpected_error);

    private val DEFAULT_ERROR_CODE = -1

    fun getErrorMessage(context: Context): ErrorMessage {
        return ErrorMessage(DEFAULT_ERROR_CODE, context.resources.getString(messageId))
    }
}