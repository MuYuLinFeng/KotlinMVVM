package com.kotlinmvvm.player

import android.os.Bundle
import com.kotlinmvvm.R
import com.kotlinmvvm.base.BaseActivity
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : BaseActivity() {

    private val playerPresenter by lazy {
        PlayerPresenter(this)
    }

    init {
        lifeProvider.addLifeListener(playerPresenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initListener()
        initDataListener()
    }

    /*
        对数据进行监听
     */
    private fun initDataListener() {
        playerPresenter.currentMusic.addListener(this) {
            println("=======currentMusic  : $it")
            //因为发生改变 更新UI
            musicTitle.text = it?.name
            musicCover.text = it?.cover
        }
        playerPresenter.currentPlayState.addListener(this) {
            println("======= currentPlayState : $it")
            it?.let {
                when (it) {
                    PlayerPresenter.PlayState.PAUSE -> {
                        playOrPause.text = "暂停"
                    }
                    PlayerPresenter.PlayState.PLAYING -> {
                        playOrPause.text = "播放"
                    }
                    PlayerPresenter.PlayState.NONE -> {

                    }
                    PlayerPresenter.PlayState.LOADING -> {

                    }
                }
            }
        }
    }

    private fun initListener() {
        playOrPause.setOnClickListener {
            playerPresenter.doPlayOrPause()
        }
        nextMusic.setOnClickListener {
            playerPresenter.playNext()
        }
        lastMusic.setOnClickListener {
            playerPresenter.playLast()
        }
    }
}