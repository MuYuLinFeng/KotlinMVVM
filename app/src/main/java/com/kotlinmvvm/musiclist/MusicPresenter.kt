package com.kotlinmvvm.musiclist

import com.kotlinmvvm.lifecycle.AbsLifeCycle
import com.kotlinmvvm.lifecycle.ILifeCycleOwner
import com.kotlinmvvm.lifecycle.LifeState
import com.kotlinmvvm.player.DataListenerContainer
import com.kotlinmvvm.player.domain.Music

class MusicPresenter(lifeOwner: ILifeCycleOwner) {
    enum class MusicLoadState {
        LOADING, EMPTY, SUCCESS, ERROR
    }

    private val model by lazy {
        MusicModel()
    }
    private val page = 1
    private val size = 10
    val loadState = DataListenerContainer<MusicLoadState>()
    val musicList = DataListenerContainer<List<Music>>()
    private val viewLifeImpl by lazy { ViewLifeImpl() }

    init {
        lifeOwner.getLifeCycleProvider().addLifeListener(viewLifeImpl)
    }

    fun getMusic() {
        //发起请求
        loadState.value = MusicLoadState.LOADING
        model.loadMusicByPage(page, size, object : MusicModel.OnMusicLoadResult {
            override fun onSuccess(result: List<Music>) {
                musicList.value = result
                loadState.value = if (result.isEmpty()) {
                    MusicLoadState.EMPTY
                } else {
                    MusicLoadState.SUCCESS
                }
            }

            override fun onError(msg: String, code: Int) {
                loadState.value = MusicLoadState.ERROR
                println("error")
            }

        })
    }

    inner class ViewLifeImpl : AbsLifeCycle() {
        override fun onCreate() {
        }

        override fun onStart() {
            //启动监听
        }

        override fun onResume() {
        }

        override fun onPause() {
        }

        override fun onStop() {
            //停止监听
        }

        override fun onDestroy() {
        }

        override fun onViewLifeStateChange(state: LifeState) {
        }
    }
}