package com.mertselcukdemir.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertselcukdemir.core.data.ErrorData
import com.mertselcukdemir.ui.livedata.SingleLiveData

/**
 * Created by mertselcukdemir on 24.01.2022
 * All rights reserved.
 */

open class BaseViewModel : ViewModel() {
    var errorMessage: SingleLiveData<String> = SingleLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    protected fun handleError(errorData: ErrorData?) {
        //Exception thrown by Coroutines
        if (errorData?.errorCode == null && errorData?.errorMessage.isNullOrEmpty().not()) {
            errorMessage.value = errorData!!.errorMessage
        }
        //Exception thrown by server
        else if (errorData?.errorCode != null && errorData.errorMessage.isNullOrEmpty().not()) {
            errorData.getMessageOfError()?.let { error ->
                errorMessage.value = error
            }
        }
    }
}