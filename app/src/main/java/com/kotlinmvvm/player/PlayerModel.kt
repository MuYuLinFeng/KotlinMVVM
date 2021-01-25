package com.kotlinmvvm.player

import com.kotlinmvvm.player.domain.Music

class PlayerModel {
    fun getMusicById(idStr: String): Music {
        return Music(idStr, "cover", "url")
    }

    fun getNextSongById(idStr: String): Music {
        return Music(idStr, "cover", "url")
    }

    fun getLastSongById(idStr: String): Music {
        return Music(idStr, "cover", "url")
    }
}