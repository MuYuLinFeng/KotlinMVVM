package com.kotlinmvvm.taobao

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinmvvm.taobao.domain.LoadState
import com.kotlinmvvm.taobao.domain.OnSellData
import kotlinx.coroutines.launch

class OnSellViewModel : ViewModel() {
    private val onSellRepository by lazy {
        OnSellRepository()
    }
    val contentList =
        MutableLiveData<MutableList<OnSellData.TbkDgOptimusMaterialResponse.ResultList.MapData>>()
    val loadState = MutableLiveData<LoadState>()

    companion object {
        const val DEFAULT_PAGE = 1
        const val PAGE_SIZE = 10
    }

    private var currentPage = DEFAULT_PAGE

    fun loadContent() {
        isLoadMore = false
        listContentByPage(currentPage)
    }

    private var isLoadMore = false
    fun loadMore() {
        loadState.value = LoadState.LOAD_MORE_LOADING
        isLoadMore = true
        currentPage++

    }

    private fun listContentByPage(page: Int) {
        loadState.value = LoadState.LOADING
        viewModelScope.launch {
            try {
                val onSellData = onSellRepository.getOnSellList(page)
                val oldValue = contentList.value ?: mutableListOf()
                oldValue.addAll(onSellData.tbk_dg_optimus_material_response.result_list.map_data)
                contentList.value = oldValue
                contentList.value = onSellData.tbk_dg_optimus_material_response.result_list.map_data
                if (onSellData.tbk_dg_optimus_material_response.result_list.map_data.isEmpty()) {
                    loadState.value = if (isLoadMore) LoadState.LOAD_MORE_EMPTY else LoadState.EMPTY
                } else {
                    loadState.value = LoadState.SUCCESS
                }
            } catch (ex: NullPointerException) {
                currentPage--
                loadState.value = LoadState.LOAD_MORE_EMPTY
            } catch (ex: Exception) {
                currentPage--
                loadState.value = if (isLoadMore) LoadState.LOAD_MORE_FAILED else LoadState.ERROR
            }
        }
    }
}