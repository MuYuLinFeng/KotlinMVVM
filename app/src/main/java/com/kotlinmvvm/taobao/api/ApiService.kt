package com.kotlinmvvm.taobao.api

import com.kotlinmvvm.taobao.domain.OnSellData
import com.kotlinmvvm.taobao.domain.ResultData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object {
        const val BASE_URL = "https://api.sunofbeach.net/shop/"
    }

    @GET("onSell/{page}")
    suspend fun getOnSellList(@Path("page") page: Int):
            ResultData<OnSellData>
}