package com.laioffer.tinnews.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.laioffer.tinnews.databinding.FragmentHomeBinding
import com.laioffer.tinnews.model.Article
import com.laioffer.tinnews.model.NewsResponse
import com.laioffer.tinnews.repository.NewsRepository
import com.laioffer.tinnews.repository.NewsViewModelFactory
import com.yuyakaido.android.cardstackview.*

class HomeFragment : Fragment(), CardStackListener {
    private var viewModel: HomeViewModel? = null
    private var binding: FragmentHomeBinding? = null
    private var layoutManager: CardStackLayoutManager? = null
    private var articles: List<Article>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set up card stack view
        val swipeAdapter = CardSwipeAdapter()

        // we need to provide an listener
        // this: home fragment since we use this class to implement cardstacklistener
        // ; we can also pass in an anonymous class
        layoutManager = CardStackLayoutManager(requireContext(), this)
        layoutManager!!.setStackFrom(StackFrom.Top)
        binding!!.homeCardStackView.layoutManager = layoutManager
        binding!!.homeCardStackView.adapter = swipeAdapter

        // enable button to swipe
        binding!!.homeLikeButton.setOnClickListener { v: View? -> swipeCard(Direction.Right) }
        binding!!.homeUnlikeButton.setOnClickListener { v: View? -> swipeCard(Direction.Left) }
        val repository = NewsRepository(context)
        // this - current view
        viewModel = ViewModelProvider(this, NewsViewModelFactory(repository))
                .get(HomeViewModel::class.java)
        viewModel!!.setCountryInput("us")
        viewModel!!.topHeadlines // get the live data
                .observe(
                        viewLifecycleOwner,  // get the view's state
                        Observer { newsResponse: NewsResponse? ->  // observe callback function
                            if (newsResponse != null) {
                                articles = newsResponse.articles
                                swipeAdapter.setArticles(articles)
                            }
                        })
    }

    // programatically achieve swipe
    private fun swipeCard(direction: Direction) {
        // define setting, animation builder, set directions
        val setting = SwipeAnimationSetting.Builder()
                .setDirection(direction)
                .setDuration(Duration.Normal.duration)
                .build()
        layoutManager!!.setSwipeAnimationSetting(setting)
        binding!!.homeCardStackView.swipe()
    }

    override fun onCardDragging(direction: Direction, ratio: Float) {}

    // if swipped then we need to notify
    override fun onCardSwiped(direction: Direction) {
        if (direction == Direction.Left) {
            Log.d("CardStackView", "Unliked " + layoutManager!!.topPosition)
        } else if (direction == Direction.Right) {
            Log.d("CardStackView", "Liked " + layoutManager!!.topPosition)
            // get the previous one
            val article = articles!![layoutManager!!.topPosition - 1]
            viewModel!!.setFavoriteArticleInput(article)
        }
    }

    override fun onCardRewound() {}
    override fun onCardCanceled() {}
    override fun onCardAppeared(view: View, position: Int) {}
    override fun onCardDisappeared(view: View, position: Int) {}
}