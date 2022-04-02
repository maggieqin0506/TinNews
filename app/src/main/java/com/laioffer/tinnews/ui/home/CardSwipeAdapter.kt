package com.laioffer.tinnews.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laioffer.tinnews.R
import com.laioffer.tinnews.databinding.SwipeNewsCardBinding
import com.laioffer.tinnews.model.Article
import com.laioffer.tinnews.ui.home.CardSwipeAdapter.CardSwipeViewHolder
import com.squareup.picasso.Picasso
import java.util.*

// extends a base class
class CardSwipeAdapter : RecyclerView.Adapter<CardSwipeViewHolder>() {
    // 1. Supporting data:
    private val articles: MutableList<Article> = ArrayList()
    fun setArticles(newsList: List<Article>?) {
        articles.clear()
        articles.addAll(newsList!!)
        notifyDataSetChanged()
    }

    // 2. Adapter overrides:
    // dont need to bind every time
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardSwipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.swipe_news_card, parent, false)
        return CardSwipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardSwipeViewHolder, position: Int) {
        val article = articles[position]
        holder.titleTextView.text = article.title
        holder.descriptionTextView.text = article.description
        Picasso.get().load(article.urlToImage).into(holder.imageView)
    }

    // data size
    override fun getItemCount(): Int {
        return articles.size
    }

    // 3. CardSwipeViewHolder:
    // already referenced don't need to bind
    class CardSwipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var titleTextView: TextView
        var descriptionTextView: TextView

        init {
            val binding = SwipeNewsCardBinding.bind(itemView)
            imageView = binding.swipeCardImageView
            titleTextView = binding.swipeCardTitle
            descriptionTextView = binding.swipeCardDescription
        }
    }
}