package com.lily.kotlin.utils

/**
 * @Author rape flower
 * @Date 2018-06-28 12:34
 * @Describe 检查工具类
 * <p>
 * Provides general assert utils, which are stripped by Android SDK on
 * compile/runtime, to work on release builds
 * </p>
 */
object Inspect {

    /**
     * Will throw AssertionError, if expression is not true
     *
     * @param expression    result of your asserted condition
     * @param failedMessage message to be included in error log
     * @throws java.lang.AssertionError
     */
    fun asserts(expression: Boolean, failedMessage: String) {
        if (!expression) {
            throw AssertionError(failedMessage)
        }
    }

    /**
     * Will throw IllegalArgumentException if provided object is null on runtime
     *
     * @param argument object that should be asserted as not null
     * @param name     name of the object asserted
     * @throws java.lang.IllegalArgumentException
     */
    fun <T> notNull(argument: T, name: String): T {
        if (argument == null) {
            throw IllegalArgumentException(name + " should not be null!")
        }
        return argument
    }
}