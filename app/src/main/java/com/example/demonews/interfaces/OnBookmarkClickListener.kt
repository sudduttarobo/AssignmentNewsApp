package com.example.demonews.interfaces

interface OnBookmarkClickListener {
    fun onBookmarkClicked(title: String,description: String,image_link: String,source_name: String,news_url: String)
}