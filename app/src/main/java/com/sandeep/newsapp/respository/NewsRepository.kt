package com.sandeep.newsapp.respository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sandeep.newsapp.AppExecutors
import com.sandeep.newsapp.api.Hit
import com.sandeep.newsapp.api.HoneywellPocService
import com.sandeep.newsapp.api.NewsOnlineDataSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository that handles Repo instances.
 */
@Singleton
class NewsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val service: HoneywellPocService,
    private val online: NewsOnlineDataSource,
) {
    private lateinit var m_scope: CoroutineScope

    fun getNewList(scope: CoroutineScope,search: String): LiveData<PagedList<Hit>> {
        m_scope = scope
        return online.getNewsList(m_scope,search)

    }
}
