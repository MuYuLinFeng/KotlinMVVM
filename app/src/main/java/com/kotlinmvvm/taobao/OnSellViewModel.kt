package com.kotlinmvvm.taobao

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnSellViewModel :ViewModel(){
    val contentList = MutableLiveData<MutableList<String>>()
}