package com.lily.kotlin.model

/**
 * @Author rape flower
 * @Date 2018-07-02 18:07
 * @Describe 物流信息model
 */
data class Logistics(
        var message: String,
        var nu: String,
        var ischeck: String,
        var com: String,
        var status: String,
        var condition: String,
        var state: String,
        var data: List<Details>
)