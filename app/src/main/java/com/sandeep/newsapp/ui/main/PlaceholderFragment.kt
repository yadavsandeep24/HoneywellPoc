package com.sandeep.newsapp.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandeep.newsapp.AppExecutors
import com.sandeep.newsapp.R
import com.sandeep.newsapp.databinding.FragmentMainBinding
import com.sandeep.newsapp.di.Injectable
import com.sandeep.newsapp.ui.main.adapters.NewsListAdapter
import com.sandeep.newsapp.ui.main.listeners.NewsListAdapterInterface
import com.sandeep.newsapp.util.autoCleared
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment(), Injectable, NewsListAdapterInterface {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val pageViewModel: PageViewModel by viewModels {
        viewModelFactory
    }

    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var adapter: NewsListAdapter
    var binding by autoCleared<FragmentMainBinding>()

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
       // binding.container = pageViewModel.containers
        setUpNewsRecycler()
        subscribeUI()
        binding.svQuery.setOnQueryTextListener(queryTextListener)
    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            pageViewModel.setQuery(query)
            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            val closeButton: ImageView = binding.svQuery.findViewById(R.id.search_close_btn) as ImageView
            closeButton.visibility = View.VISIBLE
            pageViewModel.setQuery(newText)
            return true

        }
    }
    private fun setUpNewsRecycler() {
        val lm = LinearLayoutManager(context)
        binding.rvNewListDashbord.layoutManager = lm
        binding.rvNewListDashbord.itemAnimator = DefaultItemAnimator()
        binding.rvNewListDashbord.setHasFixedSize(false)
        adapter = NewsListAdapter(context as Context, lm, this, CoroutineScope(Dispatchers.Default))
        binding.rvNewListDashbord.adapter = adapter
        binding.rvNewListDashbord.isNestedScrollingEnabled = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )
        binding = dataBinding
        return binding.root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(position: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, position)
                }
            }
        }
    }

    private fun subscribeUI() {
        // Observing News Data
        pageViewModel.getNews().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.submitList(it)
            }
        })


    }

    override fun onNewsItemClicked(url: String) {
        Log.d("SAN", "URL-->$url")
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

}
