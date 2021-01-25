package com.kotlinmvvm.musiclist

import com.kotlinmvvm.player.domain.Music
import kotlin.concurrent.thread

class MusicModel {
    fun loadMusicByPage(page: Int, size: Int, callback: OnMusicLoadResult) {
        val result: ArrayList<Music> = arrayListOf()
        thread {
            for (i in size * (page - 1) until size * page) {
                result.add(Music("狼爱上羊-$i", "cover-$i", "url-$i"))
            }
            Thread.sleep(2000)
        }.start()
    }

    interface OnMusicLoadResult {
        fun onSuccess(result: List<Music>)
        fun onError(msg: String, code: Int)
    }
}