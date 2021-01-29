package com.kotlinmvvm.taobao.domain

enum class LoadState {
    LOADING,
    SUCCESS,
    EMPTY,
    ERROR,
    LOAD_MORE_LOADING,
    LOAD_MORE_FAILED,
    LOAD_MORE_EMPTY
}