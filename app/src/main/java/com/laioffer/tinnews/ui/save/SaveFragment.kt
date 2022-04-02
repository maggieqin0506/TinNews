package com.laioffer.tinnews.ui.save

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.laioffer.tinnews.databinding.FragmentSaveBinding
import com.laioffer.tinnews.model.Article
import com.laioffer.tinnews.repository.NewsRepository
import com.laioffer.tinnews.repository.NewsViewModelFactory

class SaveFragment : Fragment() {
    private var binding: FragmentSaveBinding? = null
    private var viewModel: SaveViewModel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // use binding to inflate, refer to different child view class
        binding = FragmentSaveBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    // after creating a view, need further customization
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val savedNewsAdapter = SavedNewsAdapter()
        binding!!.newsSavedRecyclerView.adapter = savedNewsAdapter
        binding!!.newsSavedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        savedNewsAdapter.setItemCallback(object : SavedNewsAdapter.ItemCallback {
            override fun onOpenDetails(article: Article?) {
                val direction = SaveFragmentDirections.actionNavigationSaveToNavigationDetails(article!!)
                NavHostFragment.findNavController(this@SaveFragment).navigate(direction)
            }

            override fun onRemoveFavorite(article: Article?) {
                viewModel!!.deleteSavedArticle(article)
            }
        })


        // create a repo and a view model
        val repository = NewsRepository(context)
        viewModel = ViewModelProvider(this, NewsViewModelFactory(repository)).get(SaveViewModel::class.java)

        // observe view model
        viewModel!!
                .allSavedArticles
                .observe( // refresh
                        viewLifecycleOwner,
                        Observer { savedArticles: List<Article?>? ->
                            if (savedArticles != null) {
                                Log.d("SaveFragment", savedArticles.toString())
                                savedNewsAdapter.setArticles(savedArticles)
                            }
                        })
    }
}