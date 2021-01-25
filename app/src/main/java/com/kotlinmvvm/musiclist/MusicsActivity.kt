package com.kotlinmvvm.musiclist

import android.os.Bundle
import com.kotlinmvvm.R
import com.kotlinmvvm.base.BaseActivity
import com.kotlinmvvm.lifecycle.ILifeCycleOwner
import kotlinx.android.synthetic.main.activity_musics.*

class MusicsActivity : BaseActivity() {
    /*
        生命周期
        onCreate
        onStart
        onResume
        onPause
        onStop
        onDestroy
     */
    private val musicPresenter by lazy {
        MusicPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musics)
        initListener()
        initDataListener()
    }

    /*
        监听数据变化
     */
    private fun initDataListener() {
        musicPresenter.musicList.addListener(this) {
            println(Thread.currentThread().name)
            println("加载结果---》$it")
            it?.let {
                musicCount.text = "加载到了-->${it.size}"
            }
        }
        musicPresenter.loadState.addListener(this) {
            println("加载状态---》$it")
        }
    }

    private fun initListener() {
        getMusicBtn.setOnClickListener {
            musicPresenter.getMusic()
        }
    }
}