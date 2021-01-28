package com.kotlinmvvm.taobao

import com.kotlinmvvm.taobao.api.RetrofitClient
import com.kotlinmvvm.taobao.domain.OnSellData

class OnSellRepository {
    suspend fun getOnSellList(page: Int): OnSellData {
        val result = RetrofitClient.apiService.getOnSellList(page)
        return result.apiData()
    }
}