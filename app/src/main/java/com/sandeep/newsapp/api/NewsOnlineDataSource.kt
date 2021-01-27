package com.sandeep.newsapp.api

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sandeep.newsapp.factory.NewsDataSource
import com.sandeep.newsapp.factory.NewsDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsOnlineDataSource@Inject constructor(
    honeywellService: HoneywellPocService
) {

    private val service = honeywellService
    var pagedList: LiveData<PagedList<Hit>>? = null


    fun getNewsList(scope:CoroutineScope,query: String): LiveData<PagedList<Hit>> {
        scope.launch{
        }
        // Call the New Data Source for Getting the List of News
        val dataSourceFactory = NewsDataSourceFactory(service,scope,query)
        val config = (PagedList.Config.Builder())
            .setPageSize(NewsDataSource.PAGE_SIZE)
            .setInitialLoadSizeHint(1)
            .build()
        pagedList = (LivePagedListBuilder(dataSourceFactory,config)).build()
        return pagedList!!
    }

}