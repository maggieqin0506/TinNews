package com.laioffer.tinnews.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.laioffer.tinnews.databinding.FragmentSearchBinding
import com.laioffer.tinnews.model.Article
import com.laioffer.tinnews.model.NewsResponse
import com.laioffer.tinnews.repository.NewsRepository
import com.laioffer.tinnews.repository.NewsViewModelFactory

class SearchFragment : Fragment() {
    private var viewModel: SearchViewModel? = null
    private var binding: FragmentSearchBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recycler view setup
        val newsAdapter = SearchNewsAdapter()
        newsAdapter.setItemCallback { article: Article? ->
            val direction = SearchFragmentDirections.actionNavigationSearchToNavigationDetails(article!!)
            NavHostFragment.findNavController(this@SearchFragment).navigate(direction)
        }
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding!!.newsResultsRecyclerView.layoutManager = gridLayoutManager
        binding!!.newsResultsRecyclerView.adapter = newsAdapter
        binding!!.newsSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // two cb
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isEmpty()) {
                    viewModel!!.setSearchInput(query)
                }
                binding!!.newsSearchView.clearFocus()
                // T: already processed the cb
                // F: not yet processed the cb
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        val repository = NewsRepository(context)
        viewModel = ViewModelProvider(this, NewsViewModelFactory(repository))
                .get(SearchViewModel::class.java)
        viewModel!!
                .searchNews()
                .observe(
                        viewLifecycleOwner,
                        Observer { newsResponse: NewsResponse? ->
                            if (newsResponse != null) {
                                Log.d("SearchFragment", newsResponse.toString())
                                newsAdapter.setArticles(newsResponse.articles)
                            }
                        })
    }
}