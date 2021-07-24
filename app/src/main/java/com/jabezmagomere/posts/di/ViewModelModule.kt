package com.jabezmagomere.posts.di

import com.jabezmagomere.posts.ui.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        MainActivityViewModel(get())
    }
}
