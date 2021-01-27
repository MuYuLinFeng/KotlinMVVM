package com.kotlinmvvm.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlinmvvm.R
import com.kotlinmvvm.base.BaseActivity
import kotlinx.android.synthetic.main.activity_flow_player_controller.*

class FlowPlayerControllerActivity : BaseActivity() {
    private val playerPresenter by lazy {
        PlayerPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_player_controller)
        initListener()
        initDataListener()
    }

    private fun initDataListener() {
        playerPresenter.currentPlayState.addListener(this) {
            it?.let {
                when (it) {
                    PlayerPresenter.PlayState.PAUSE -> {
                        flowPlayOrPause.text = "暂停"
                    }
                    PlayerPresenter.PlayState.PLAYING -> {
                        flowPlayOrPause.text = "播放"
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
        flowPlayOrPause.setOnClickListener {
            playerPresenter.doPlayOrPause()
        }
    }
}