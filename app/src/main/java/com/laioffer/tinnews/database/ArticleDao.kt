package com.laioffer.tinnews.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.laioffer.tinnews.model.Article

@Dao
interface ArticleDao {
    @Insert
    fun saveArticle(article: Article?)

    @get:Query("SELECT * FROM article")
    val allArticles: LiveData<List<Article?>?>?

    @Delete
    fun deleteArticle(article: Article?)
}