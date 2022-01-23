package com.mertselcukdemir.core.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.EOFException

/**
 * Created by mertselcukdemir on 24.01.2022
 * All rights reserved.
 */

/**
 * @param errorCode that can be used to convey the errors of a client’s request.
 * @param errorMessage provides a client or developer with information to help troubleshoot the problem
 */
data class ErrorData(val errorCode: Int?, val errorMessage: String?) {
    fun getMessageOfError(): String? {
        if (errorCode == null || errorMessage == null) {
            return null
        }
        return try {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val jsonAdapter = moshi.adapter(ErrorResponse::class.java)
            val errorResponse = jsonAdapter.fromJson(errorMessage)!!
            errorResponse.message
        } catch (e: EOFException) {
            e.printStackTrace()
            null
        }
    }
}
