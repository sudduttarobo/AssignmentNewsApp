package com.example.demonews.ui.bookmarkPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demonews.R
import com.example.demonews.adapter.BookmarkNewsAdapter
import com.example.demonews.databinding.ActivityBookmarkBinding
import com.example.demonews.room.BookmarkDataBase
import com.example.demonews.room.BookmarkTableModel
import com.example.demonews.utility.SimpleDividerItemDecorationMenu
import java.util.ArrayList

class BookmarkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookmarkBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var bookmarkNewsAdapter: BookmarkNewsAdapter
   // private lateinit var bookmarkDataBase: BookmarkDataBase
    private lateinit var dataArrayList: ArrayList<BookmarkTableModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookmark)

        initBookmarkRecycler()
      //  bookmarkDataBase = BookmarkDataBase.getDatabaseClient(this)
        dataArrayList.clear()
      //  dataArrayList.addAll(bookmarkDataBase.bookmarkDao().getAll())
        bookmarkNewsAdapter.notifyDataSetChanged()
    }

    private fun initBookmarkRecycler() {
        dataArrayList = ArrayList<BookmarkTableModel>()
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvBookmarkList.layoutManager = linearLayoutManager
        bookmarkNewsAdapter = BookmarkNewsAdapter(this, dataArrayList)
        // bookmarkNewsAdapter.setOnNewsItemClickListener(this)
        // bookmarkNewsAdapter.setOnBookmarkClickListener(this)
        binding.rvBookmarkList.adapter = bookmarkNewsAdapter
        binding.rvBookmarkList.isNestedScrollingEnabled = false
        binding.rvBookmarkList.addItemDecoration(SimpleDividerItemDecorationMenu(this))
    }
}