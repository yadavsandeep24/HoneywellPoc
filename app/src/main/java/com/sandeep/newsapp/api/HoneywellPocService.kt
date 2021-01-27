package com.sandeep.newsapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * REST API access points
 */
interface HoneywellPocService {


    @GET("search")
    fun getNewsAsync(@Query("query") query :String, @Query("page") page:Int)
            : Call<NewsResponse>

}
