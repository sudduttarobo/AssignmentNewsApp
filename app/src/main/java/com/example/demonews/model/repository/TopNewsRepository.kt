package com.example.demonews.model.repository

import com.example.demonews.model.api.AllApi
import com.example.demonews.model.entity.TopHeadlineResponse
import com.example.demonews.utility.NEWS_API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TopNewsRepository(private val allAPI: AllApi) {

    lateinit var topHeadlineResponse: TopHeadlineResponse
    lateinit var popularNewsResponse: TopHeadlineResponse

    suspend fun fetchTopHeadlines() {
        withContext(Dispatchers.IO) {
            topHeadlineResponse = allAPI.performTopNews(
                NEWS_API_KEY
            ).body()!!
        }
    }

    suspend fun fetchPopularNews(sort: String) {
        withContext(Dispatchers.IO) {
            popularNewsResponse = allAPI.performPopularNews(
                sort,
                NEWS_API_KEY
            ).body()!!
        }
    }

}