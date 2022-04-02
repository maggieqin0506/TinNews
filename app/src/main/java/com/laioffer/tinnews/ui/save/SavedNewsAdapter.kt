package com.laioffer.tinnews.ui.save

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laioffer.tinnews.R
import com.laioffer.tinnews.databinding.SavedNewsItemBinding
import com.laioffer.tinnews.model.Article
import com.laioffer.tinnews.ui.save.SavedNewsAdapter.SavedNewsViewHolder
import java.util.*

class SavedNewsAdapter : RecyclerView.Adapter<SavedNewsViewHolder>() {
    interface ItemCallback {
        fun onOpenDetails(article: Article?)
        fun onRemoveFavorite(article: Article?)
    }

    private var itemCallback: ItemCallback? = null
    fun setItemCallback(itemCallback: ItemCallback?) {
        this.itemCallback = itemCallback
    }

    // 1. set articles and support data
    private val articles: MutableList<Article> = ArrayList()
    fun setArticles(newsList: List<Article>?) {
        articles.clear()
        articles.addAll(newsList!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.saved_news_item, parent, false)
        return SavedNewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedNewsViewHolder, position: Int) {
        val article = articles[position]
        holder.authorTextView.text = article.author
        holder.descriptionTextView.text = article.description
        holder.favoriteIcon.setOnClickListener { v: View? -> itemCallback!!.onRemoveFavorite(article) }
        // when click other than the heart, go to detail page
        holder.itemView.setOnClickListener { v: View? -> itemCallback!!.onOpenDetails(article) }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class SavedNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var authorTextView: TextView
        var descriptionTextView: TextView
        var favoriteIcon: ImageView

        init {
            val binding = SavedNewsItemBinding.bind(itemView)
            authorTextView = binding.savedItemAuthorContent
            descriptionTextView = binding.savedItemDescriptionContent
            favoriteIcon = binding.savedItemFavoriteImageView
        }
    }
}