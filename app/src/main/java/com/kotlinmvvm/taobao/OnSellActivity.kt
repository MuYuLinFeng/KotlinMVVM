package com.kotlinmvvm.taobao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlinmvvm.R
import com.kotlinmvvm.taobao.adapter.OnSellListAdapter
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
        }.loadContent()
    }

    private fun initView() {
        rvOnSell.run {
            layoutManager = LinearLayoutManager(this@OnSellActivity)
            adapter = mAdapter
        }
    }
}