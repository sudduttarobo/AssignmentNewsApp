package com.example.demonews.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

data class TopHeadlineResponse(
        val articles: List<Article>,
        val status: String,
        val totalResults: Int
)

@Entity(
        tableName = "articles"
)

data class Article(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        val author: String,
        val content: String,
        val description: String,
        val publishedAt: String,
        val source: Source,
        val title: String,
        val url: String,
        val urlToImage: String
)


data class Source(
        val id: String,
        val name: String
)