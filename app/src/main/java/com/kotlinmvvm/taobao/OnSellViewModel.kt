package com.kotlinmvvm.taobao

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinmvvm.taobao.domain.OnSellData
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class OnSellViewModel : ViewModel() {
    private val onSellRepository by lazy {
        OnSellRepository()
    }
    val contentList =
        MutableLiveData<ArrayList<OnSellData.TbkDgOptimusMaterialResponse.ResultList.MapData>>()

    companion object {
        const val DEFAULT_PAGE = 1
        const val PAGE_SIZE = 10
    }

    private var currentPage = DEFAULT_PAGE

    fun loadContent() {
        listContentByPage(currentPage)
    }

    fun loadMore() {

    }

    private fun listContentByPage(page: Int) {
        viewModelScope.launch {
            val onSellData = onSellRepository.getOnSellList(page)
            contentList.value = onSellData.tbk_dg_optimus_material_response.result_list.map_data
        }
    }
}