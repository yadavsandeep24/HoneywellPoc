package com.sandeep.newsapp.factory

import androidx.paging.DataSource
import com.sandeep.newsapp.api.Hit
import com.sandeep.newsapp.api.HoneywellPocService
import kotlinx.coroutines.CoroutineScope

class NewsDataSourceFactory(
    val service: HoneywellPocService?,
    val scope: CoroutineScope,
    val query : String
): DataSource.Factory<Int, Hit>() {

    override fun create(): DataSource<Int, Hit> = NewsDataSource(service!!,scope,query)
}
