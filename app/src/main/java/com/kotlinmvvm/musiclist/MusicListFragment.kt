package com.kotlinmvvm.musiclist

import com.kotlinmvvm.base.BaseFragment

class MusicListFragment : BaseFragment() {
    private val musicPresenter by lazy {
        MusicPresenter(lifeProvider)
    }

    init {
        lifeProvider.addLifeListener(musicPresenter)
    }

}