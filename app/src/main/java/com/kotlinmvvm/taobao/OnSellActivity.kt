package com.kotlinmvvm.taobao

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinmvvm.R
import com.kotlinmvvm.taobao.adapter.OnSellListAdapter
import com.kotlinmvvm.taobao.domain.LoadState
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import kotlinx.android.synthetic.main.activity_on_sell.*

class OnSellActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(OnSellViewModel::class.java)
    }
    private val mAdapter by lazy {
        OnSellListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_sell)
        initView()
        initObserver()
        viewModel.loadContent()
    }

    private fun initObserver() {
        viewModel.apply {
            contentList.observe(this@OnSellActivity, Observer {
                //内容列表更新
                //更新适配器
                mAdapter.setData(it)
            })
            loadState.observe(this@OnSellActivity, Observer { loadState ->
                if (loadState != LoadState.LOAD_MORE_LOADING) {
                    //不是刷新的loading才去隐藏
                    hideAll()
                }
                when (loadState) {
                    LoadState.LOADING -> {
                    }
                    LoadState.SUCCESS -> {
                        tkRefreshLayout.finishLoadmore()
                    }
                    LoadState.ERROR -> {
                    }
                    LoadState.EMPTY -> {
                    }
                    LoadState.LOAD_MORE_FAILED -> {
                        tkRefreshLayout.finishLoadmore()
                    }
                    LoadState.LOAD_MORE_LOADING -> {
                        tkRefreshLayout.startLoadMore()
                    }
                    LoadState.LOAD_MORE_EMPTY -> {
                        tkRefreshLayout.finishLoadmore()
                    }
                    else -> {

                    }
                }
            })
            tkRefreshLayout.run {
                setEnableLoadmore(true)
                setEnableRefresh(true)
                setEnableOverScroll(true)
                setOnRefreshListener(object : RefreshListenerAdapter() {
                    override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                        super.onLoadMore(refreshLayout)
                        viewModel.loadMore()
                    }
                })
            }
        }.loadContent()
    }

    private fun hideAll() {

    }

    private fun initView() {
        rvOnSell.run {
            layoutManager = LinearLayoutManager(this@OnSellActivity)
            adapter = mAdapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.apply {
                        left = 8
                        top = 8
                        right = 8
                        bottom = 8
                    }
                }
            })
        }
    }
}