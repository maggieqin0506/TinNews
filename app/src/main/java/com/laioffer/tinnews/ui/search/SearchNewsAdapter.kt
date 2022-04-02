package com.laioffer.tinnews.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laioffer.tinnews.R
import com.laioffer.tinnews.databinding.SearchNewsItemBinding
import com.laioffer.tinnews.model.Article
import com.laioffer.tinnews.ui.search.SearchNewsAdapter.SearchNewsViewHolder
import com.squareup.picasso.Picasso
import java.util.*

// generic type, need to specify which viewholder
class SearchNewsAdapter : RecyclerView.Adapter<SearchNewsViewHolder>() {
    interface ItemCallback {
        fun onOpenDetails(article: Article?)
    }

    private var itemCallback: ItemCallback? = null
    fun setItemCallback(itemCallback: ItemCallback?) {
        this.itemCallback = itemCallback
    }

    // 1. Supporting data:
    private val articles: MutableList<Article> = ArrayList()
    fun setArticles(newsList: List<Article>?) {
        articles.clear()
        articles.addAll(newsList!!)
        notifyDataSetChanged()
        // recycler base class achieve this, trigger onBindViewHolder and getItemCount
    }

    // 2. Adapter overrides:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNewsViewHolder {
        // only need to call bind() once
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_news_item,
                parent, false)
        return SearchNewsViewHolder(view)
    }

    // unlimited call
    override fun onBindViewHolder(holder: SearchNewsViewHolder, position: Int) {
        // tell the cb which position to reuse
        // dynamically reuse based on the memory
        val article = articles[position]
        holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_24dp)
        holder.itemTitleTextView.text = article.title
        Picasso.get().load(article.urlToImage).into(holder.itemImageView)
        holder.itemView.setOnClickListener { v: View? -> itemCallback!!.onOpenDetails(article) }
    }

    // tell them how many data are there
    override fun getItemCount(): Int {
        return articles.size
    }

    // 3. SearchNewsViewHolder:
    class SearchNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var favoriteImageView: ImageView
        var itemImageView: ImageView
        var itemTitleTextView: TextView

        init {
            // bind() high cost 20ms
            val binding = SearchNewsItemBinding.bind(itemView)
            favoriteImageView = binding.searchItemFavorite
            itemImageView = binding.searchItemImage
            itemTitleTextView = binding.searchItemTitle
        }
    }
}