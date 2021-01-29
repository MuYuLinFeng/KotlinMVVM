package com.kotlinmvvm.taobao.api

data class ApiException(val code: Int, override val message: String?) : RuntimeException() {
}