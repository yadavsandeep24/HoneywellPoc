package com.sandeep.newsapp.ui.main.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.newsapp.R
import com.sandeep.newsapp.api.Hit
import com.sandeep.newsapp.ui.main.listeners.NewsListAdapterInterface
import kotlinx.android.synthetic.main.row_news_dashboard.view.*
import kotlinx.coroutines.CoroutineScope

class NewsListAdapter(
    context: Context,
    layoutManager: LinearLayoutManager,
    newsInterface: NewsListAdapterInterface,
    private val scope: CoroutineScope
) : PagedListAdapter<Hit, NewsListAdapter.NewsViewHolder>(
    DIFF_CALLBACK
) {

    private val ctx = context
    private val manager = layoutManager
    private val listener = newsInterface

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Hit>() {
            override fun areContentsTheSame(p0: Hit, p1: Hit): Boolean {
                return p0.objectID == p1.objectID
            }

            override fun areItemsTheSame(p0: Hit, p1: Hit): Boolean {
                return p1.title == p0.title
            }
        }
        const val NEWS_ITEM = 1
        const val LOADING = 2
        const val HEADER = 3
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NewsViewHolder {
        Log.d("NewsListRecycler", "OnCreate View Holder")
        val view = when (p1) {
            NEWS_ITEM -> {
                LayoutInflater.from(ctx).inflate(R.layout.row_news_dashboard, p0, false)
            }
            LOADING -> {
                LayoutInflater.from(ctx).inflate(R.layout.item_loading, p0, false)
            }
            else -> {
                LayoutInflater.from(ctx).inflate(R.layout.row_news_header, p0, false)
            }
        }
        return NewsViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER
            itemCount - 1 -> LOADING
            else -> NEWS_ITEM
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        when (getItemViewType(position)) {
            NEWS_ITEM -> {
                Log.d("NewsListRecycler", "Bind View Holder : News Item $position")
                if (position == 1) {
                    viewHolder.itemView.news_row_item.background =
                        ctx.getDrawable(R.drawable.top_curve_background)
                }
                news?.let {
                    it.title?.let {
                        viewHolder.itemView.newsTitle.text = news.title
                    }

                    it.author?.let {
                        viewHolder.itemView.news_auther.text = "Auther: "+news.author
                    }
                    it.num_comments.let {
                        viewHolder.itemView.news_comment_count.text = "Comment: "+news.num_comments
                    }


                }
                viewHolder.itemView.setOnClickListener {
                    news?.url?.let { it1 -> listener.onNewsItemClicked(it1) }
                }

            }
            LOADING -> {
                // To Make changes in Loading Screen.
            }
        }
    }


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}