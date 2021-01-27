package com.sandeep.newsapp.factory

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.sandeep.newsapp.api.Hit
import com.sandeep.newsapp.api.HoneywellPocService
import com.sandeep.newsapp.api.NewsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsDataSource @Inject constructor(
    val service: HoneywellPocService,
    val scope: CoroutineScope,
    val query : String
): PageKeyedDataSource<Int, Hit>() {

    companion object {
        var PAGE_SIZE = 10
        var FIRST_PAGE = 1
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Hit>) {
        callback.onResult( listOf(Hit()) , null , FIRST_PAGE )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Hit>) {
        val call = service.getNewsAsync( query, params.key)
        Log.d("SAN","query -->"+query+"/params.key-->"+params.key)
        call.enqueue(object : Callback<NewsResponse>{
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                // Parse the body
                val newsItems = response.body()?.hits
                val newsToSave = arrayListOf<Hit>()
                if (response.isSuccessful && response.body()!=null && newsItems!=null
                    && newsItems.isNotEmpty()
                ){
                    val key =  params.key + 1
                    // Then add the News Items to the List
                    val list = mutableListOf<Hit>()
                    newsItems.forEach {
                        if (!it.title.isNullOrBlank()){
                            list.add(it)
                            newsToSave.add(it)
                        }
                    }
                    // Save the rest of the remaining News
                    scope.launch(Dispatchers.Default){

                    }
                    callback.onResult(list , key)
                }else{
                    // Do Nothing
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Hit>) {
        val call = service.getNewsAsync( query , params.key)
        Log.d("SAN","query -->"+query+"/params.key-->"+params.key)
        call.enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    val key = if (params.key > 1) params.key - 1 else null
                    val list = mutableListOf<Hit>()
                    try {
                        list.addAll(response.body()!!.hits)
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                    callback.onResult(list , key)
                }else{
                    // Do nothing
                }
            }
        })
    }


}