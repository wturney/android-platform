package com.wtl.base.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wtl.base.util.NonNullMutableLiveData
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
