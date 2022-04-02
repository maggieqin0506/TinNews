package com.laioffer.tinnews.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

// serializable: enable cross-process communication
@Entity
class Article : Serializable {
    @kotlin.jvm.JvmField
    var author: String? = null
    @kotlin.jvm.JvmField
    var content: String? = null
    @kotlin.jvm.JvmField
    var description: String? = null
    @kotlin.jvm.JvmField
    var publishedAt: String? = null
    @kotlin.jvm.JvmField
    var title: String? = null

    @kotlin.jvm.JvmField
    @PrimaryKey
    var url: String = null
    @kotlin.jvm.JvmField
    var urlToImage: String? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val article = o as Article
        return author == article.author &&
                content == article.content &&
                description == article.description &&
                publishedAt == article.publishedAt &&
                title == article.title &&
                url == article.url &&
                urlToImage == article.urlToImage
    }

    override fun hashCode(): Int {
        return Objects.hash(author, content, description, publishedAt, title, url, urlToImage)
    }

    override fun toString(): String {
        return "Article{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                '}'
    }
}