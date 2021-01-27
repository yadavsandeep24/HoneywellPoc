package com.sandeep.newsapp.di

import com.sandeep.newsapp.ui.main.PlaceholderFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePlaceHolderFragment(): PlaceholderFragment
}
