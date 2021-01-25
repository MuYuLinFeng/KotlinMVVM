package com.kotlinmvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlinmvvm.lifecycle.ILifeCycleOwner
import com.kotlinmvvm.lifecycle.LifeCycleProvider
import com.kotlinmvvm.lifecycle.LifeState

open class BaseActivity : AppCompatActivity(), ILifeCycleOwner {
    val lifeProvider by lazy {
        LifeCycleProvider()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifeProvider.makeLifeState(LifeState.CREATE)
    }

    override fun onStart() {
        super.onStart()
        lifeProvider.makeLifeState(LifeState.START)
    }

    override fun onResume() {
        super.onResume()
        lifeProvider.makeLifeState(LifeState.RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifeProvider.makeLifeState(LifeState.PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifeProvider.makeLifeState(LifeState.STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifeProvider.makeLifeState(LifeState.DESTROY)
    }

    override fun getLifeCycleProvider(): LifeCycleProvider {
        return lifeProvider
    }
}