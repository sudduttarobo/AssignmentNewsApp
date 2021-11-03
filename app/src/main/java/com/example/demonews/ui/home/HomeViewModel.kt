package com.example.demonews.ui.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demonews.R
import com.example.demonews.model.entity.Article
import com.example.demonews.model.repository.TopNewsRepository
import com.example.demonews.utility.InternetUtil
import com.example.demonews.utility.Screens
import kotlinx.coroutines.launch
import java.util.ArrayList

class HomeViewModel(private val topNewsRepository: TopNewsRepository) : ViewModel() {

    val hideKeyBoard: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorMessageFailure: MutableLiveData<String> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val loadingScreen = MutableLiveData<Int>()
    val strNewsHead = MutableLiveData<String>()
    val strNewsDesc = MutableLiveData<String>()
    val strNewsSource = MutableLiveData<String>()
    val strNewsImage = MutableLiveData<String>()
    val strNewsLink = MutableLiveData<String>()
    val pNewsList = MutableLiveData<List<Article>>()


    fun goToSearchPage() {
        loadingScreen.postValue(Screens.SEARCH.ordinal)
    }

    fun goToBookmarkPage() {
        loadingScreen.postValue(Screens.BOOKMARK.ordinal)
    }

    fun goToDetailsPage() {
        loadingScreen.postValue(Screens.DETAILS.ordinal)
    }


    fun callTopNews() {
        hideKeyBoard.postValue(true)

        if (!InternetUtil.isInternetOn()) {
            errorMessage.value = R.string.no_internet_msg
            return
        }

        viewModelScope.launch {
            try {
                onRetrieveStart()
                topNewsRepository.fetchTopHeadlines()

                retrieveTopNewsDetails()
            } catch (e: Exception) {
                errorMessage.value = R.string.something_went_wrong_pls_try_again
                loadingVisibility.value = View.GONE
            }
        }
    }

    fun callPopularNews() {
        hideKeyBoard.postValue(true)

        if (!InternetUtil.isInternetOn()) {
            errorMessage.value = R.string.no_internet_msg
            return
        }

        viewModelScope.launch {
            try {
                onRetrieveStart()
                topNewsRepository.fetchPopularNews("popularity")

                retrievePopularDetails()
            } catch (e: Exception) {
                errorMessage.value = R.string.something_went_wrong_pls_try_again
                loadingVisibility.value = View.GONE
            }
        }
    }

    private fun onRetrieveStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun retrieveTopNewsDetails() {
        if (topNewsRepository.topHeadlineResponse.status == "ok") {

            loadingVisibility.value = View.GONE

            strNewsHead.value = topNewsRepository.topHeadlineResponse.articles[0].title
            strNewsDesc.value = topNewsRepository.topHeadlineResponse.articles[0].description
            strNewsImage.value = topNewsRepository.topHeadlineResponse.articles[0].urlToImage
            strNewsSource.value = topNewsRepository.topHeadlineResponse.articles[0].source.name
            strNewsLink.value = topNewsRepository.topHeadlineResponse.articles[0].url

            loadingScreen.postValue(Screens.HOME.ordinal)
        } else {
            loadingVisibility.value = View.GONE
            errorMessageFailure.postValue(topNewsRepository.topHeadlineResponse.status)
        }
    }

    private fun retrievePopularDetails() {
        if (topNewsRepository.popularNewsResponse.status == "ok") {

            loadingVisibility.value = View.GONE
            pNewsList.postValue(topNewsRepository.popularNewsResponse.articles)
            loadingScreen.postValue(Screens.POPULAR_NEWS.ordinal)

        } else {
            loadingVisibility.value = View.GONE
            errorMessageFailure.postValue(topNewsRepository.popularNewsResponse.status)
        }
    }

}
