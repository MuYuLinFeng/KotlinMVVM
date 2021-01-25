package com.kotlinmvvm.player

import com.kotlinmvvm.player.domain.Music

class MusicPlayer {
    fun play(music: Music) {
        println("play :$music")
    }

    fun pause(music: Music) {
        println("pause :$music")
    }
}