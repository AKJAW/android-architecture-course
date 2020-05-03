package com.techyourchance.mvc.common.dependencyinjection

import com.techyourchance.mvc.common.Constants
import com.techyourchance.mvc.networking.StackoverflowApi
import com.techyourchance.mvc.screens.common.dialogs.DialogsEventBus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CompositionRoot{

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val dialogEventBus: DialogsEventBus = DialogsEventBus()

    val stackOverflowApi: StackoverflowApi by lazy {
        retrofit.create(StackoverflowApi::class.java)
    }

}