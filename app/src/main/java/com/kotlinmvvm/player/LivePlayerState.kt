package com.kotlinmvvm.player

import androidx.lifecycle.LiveData

class LivePlayerState private constructor() : LiveData<PlayerPresenter.PlayState>() {
    companion object {
        val instance by lazy {
            LivePlayerState()
        }
    }

    public override fun postValue(value: PlayerPresenter.PlayState) {
        super.postValue(value)
    }
}