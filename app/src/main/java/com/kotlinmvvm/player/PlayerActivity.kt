package com.kotlinmvvm.player

import android.os.Bundle
import androidx.lifecycle.Observer
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

    private val livePlayerObserver by lazy {
        LivePlayerStateObserver()
    }

    class LivePlayerStateObserver : Observer<PlayerPresenter.PlayState> {
        override fun onChanged(t: PlayerPresenter.PlayState?) {
            println("$t")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        LivePlayerState.instance.removeObserver(livePlayerObserver)
    }

    /*
        对数据进行监听
     */
    private fun initDataListener() {
        LivePlayerState.instance.observeForever(livePlayerObserver)
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