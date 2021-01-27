package com.sandeep.newsapp.api

import com.google.gson.annotations.Expose

data class NewsResponse(
    @Expose
    val exhaustiveNbHits: Boolean,

    @Expose
    val hits: List<Hit>,

    @Expose
    val hitsPerPage: Int,

    @Expose
    val nbHits: Int,

    @Expose
    val nbPages: Int,

    @Expose
    val page: Int,

    @Expose
    val params: String,

    @Expose
    val processingTimeMS: Int,

    @Expose
    val query: String
)