package com.example.demonews.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demonews.R
import com.example.demonews.adapter.PopularNewsAdapter
import com.example.demonews.databinding.ActivityMainBinding
import com.example.demonews.interfaces.OnBookmarkClickListener
import com.example.demonews.interfaces.OnPopularNewsClickListener
import com.example.demonews.ui.bookmarkPage.BookmarkActivity
import com.example.demonews.ui.detailsPage.NewsDetailsActivity
import com.example.demonews.ui.searchPage.SearchActivity
import com.example.demonews.utility.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class MainActivity : AppCompatActivity(), OnPopularNewsClickListener, OnBookmarkClickListener {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityMainBinding

    private lateinit var popularNewsAdapter: PopularNewsAdapter

   // private lateinit var bookmarkDataBase: BookmarkDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = getViewModel()

       // bookmarkDataBase = BookmarkDataBase.getDatabaseClient(this)
        initPopularRecycler()

        viewModel.callTopNews()

        viewModel.errorMessage.observe(this, Observer {
            showSnackMessage(this, it, binding.root)
        })

        viewModel.errorMessageFailure.observe(this, Observer {
            showSnackMessageError(this, it, binding.root)
        })


        viewModel.hideKeyBoard.observe(this, Observer {
            if (viewModel.hideKeyBoard.value == true) {
                if (currentFocus != null) {
                    val inputManager =
                        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                }
            }
        })

        viewModel.pNewsList.observe(this, Observer {
            popularNewsAdapter.differ.submitList(it)

        })



        viewModel.loadingScreen.observe(this, Observer {
            when (it) {

                Screens.DETAILS.ordinal -> {
                    val intent = Intent(this, NewsDetailsActivity::class.java)
                    intent.putExtra(NEWS_LINK, viewModel.strNewsLink.value)
                    startActivity(intent)

                }

                Screens.BOOKMARK.ordinal -> {
                    val intent = Intent(this, BookmarkActivity::class.java)
                    startActivity(intent)

                }

                Screens.SEARCH.ordinal -> {
                    val intent = Intent(this, SearchActivity::class.java)
                    startActivity(intent)
                }

                Screens.HOME.ordinal -> {
                    viewModel.callPopularNews()
                }

                Screens.POPULAR_NEWS.ordinal -> {

                }


            }
        })

        binding.viewmodel = viewModel
    }

    private fun initPopularRecycler() {
        popularNewsAdapter = PopularNewsAdapter()
        binding.rvPopularNews.apply {
            adapter=popularNewsAdapter
            layoutManager=LinearLayoutManager(this@MainActivity)
            isNestedScrollingEnabled=false
            addItemDecoration(SimpleDividerItemDecorationMenu(this@MainActivity))
        }
        popularNewsAdapter.setOnNewsItemClickListener(this)
        popularNewsAdapter.setOnBookmarkClickListener(this)
    }

    override fun onNewsItemClick(news_url: String) {
        val intent = Intent(this, NewsDetailsActivity::class.java)
        intent.putExtra(NEWS_LINK, news_url)
        startActivity(intent)

    }

    override fun onBookmarkClicked(
        title: String,
        description: String,
        image_link: String,
        source_name: String,
        news_url: String
    ) {
//        val bookmark = BookmarkTableModel(
//            title = title,
//            desc = description,
//            image = image_link,
//            source = source_name,
//            urlNews = news_url
//        )
     //   bookmarkDataBase.bookmarkDao().insertAll(bookmark)
    }

}