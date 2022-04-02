package com.laioffer.tinnews.model

import java.util.*

class NewsResponse {
    var totalResults: Int? = null
    @kotlin.jvm.JvmField
    var articles: List<Article>? = null
    var code: String? = null
    var message: String? = null
    var status: String? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as NewsResponse
        return totalResults == that.totalResults &&
                articles == that.articles &&
                code == that.code &&
                message == that.message &&
                status == that.status
    }

    override fun hashCode(): Int {
        return Objects.hash(totalResults, articles, code, message, status)
    }

    override fun toString(): String {
        return "NewsResponse{" +
                "totalResults=" + totalResults +
                ", articles=" + articles +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}'
    }
}