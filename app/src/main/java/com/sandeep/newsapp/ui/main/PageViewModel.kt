package com.sandeep.newsapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.sandeep.newsapp.api.Hit
import com.sandeep.newsapp.respository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PageViewModel @Inject constructor(repository: NewsRepository): ViewModel() {
    var pagedList: LiveData<PagedList<Hit>>
    private val uiScope = CoroutineScope(Dispatchers.Default)
    fun getNews() = pagedList
    private val newsSearch: MutableLiveData<String> = MutableLiveData()
    init {
        newsSearch.postValue("")
        pagedList = Transformations.switchMap(newsSearch) { input: String? ->
            Log.d("SAN", "input-->$input")
            if (input == null || input.isEmpty()) {
                repository.getNewList(uiScope,"")
            } else {
                repository.getNewList(uiScope,input)
            }
        }
    }

    fun  setQuery(query:String){
        newsSearch.postValue(query)
    }
}