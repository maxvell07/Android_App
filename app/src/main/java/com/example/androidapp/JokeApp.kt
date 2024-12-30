package com.example.androidapp

import android.app.Application

class JokeApp :Application(){
    lateinit var viewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()
        ManageResources.Base(this)
        viewModel = MainViewModel(TestModel(ManageResources.Base(this)))
    }
}