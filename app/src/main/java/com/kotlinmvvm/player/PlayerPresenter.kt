package com.kotlinmvvm.player

import com.kotlinmvvm.lifecycle.AbsLifeCycle
import com.kotlinmvvm.lifecycle.ILifeCycle
import com.kotlinmvvm.lifecycle.LifeState
import com.kotlinmvvm.player.domain.Music

/**
 * 播放音乐
 * 暂停音乐
 * 上一首
 * 下一首
 * =================
 * 播放的状态
 * - 通知UI改变成播放状态
 * - 通知UI进度的变化
 * 上一首，下一首
 * - 通知UI歌曲标题变化
 * - 通知UI歌曲封面变化
 * 暂停音乐
 * - 更新UI状态为暂停
 *
 * 相关数据
 * 当前歌曲
 * 当前状态
 */
class PlayerPresenter private constructor() : AbsLifeCycle() {
    var currentMusic = DataListenerContainer<Music>()
    var currentPlayState = DataListenerContainer<PlayState>()
    private val playerModel by lazy {
        PlayerModel()
    }
    private val musicPlayer by lazy {
        MusicPlayer()
    }

    companion object {
        val instance by lazy {
            PlayerPresenter()
        }
    }

    init {
        currentPlayState.value = PlayState.NONE
    }


    enum class PlayState {
        NONE, PLAYING, PAUSE, LOADING
    }

    fun doPlayOrPause() {
        currentMusic.value = getSongById()
        if (currentPlayState.value == PlayState.PLAYING) {
            currentPlayState.value = PlayState.PAUSE
            musicPlayer.pause(currentMusic.value!!)
        } else {
            musicPlayer.play(currentMusic.value!!)
            currentPlayState.value = PlayState.PLAYING
        }
    }

    fun playLast() {
        currentPlayState.value = PlayState.PLAYING
        currentMusic.value = getLastSongById()
        musicPlayer.play(currentMusic.value!!)
    }

    fun playNext() {
        currentPlayState.value = PlayState.PLAYING
        currentMusic.value = getNextSongById()
        musicPlayer.play(currentMusic.value!!)

    }

    private fun getSongById(): Music {
        return playerModel.getMusicById("葬爱家族")
    }

    private fun getNextSongById(): Music {
        return playerModel.getNextSongById("黑化家族")
    }

    private fun getLastSongById(): Music {
        return playerModel.getLastSongById("流氓子家族")
    }

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