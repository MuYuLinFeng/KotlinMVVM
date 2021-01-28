package com.kotlinmvvm.taobao.api

import java.lang.RuntimeException

data class ApiException(val code:Int, override val message: String?):RuntimeException() {
}