package com.sandeep.newsapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sandeep.newsapp.ui.main.PageViewModel
import com.sandeep.newsapp.viewmodel.HoneywellPocViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(PageViewModel::class)
    abstract fun bindPageViewModel(pageViewModel: PageViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: HoneywellPocViewModelFactory): ViewModelProvider.Factory
}
