package com.example.demonews.utility

import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.example.demonews.R
import org.jetbrains.anko.design.snackbar


const val BASE_URL: String = "https://newsapi.org/v2/"

const val API_TOP_HEADLINE: String = "top-headlines?country=in"
const val API_POPULAR_NEWS: String = "everything?q=google&from=2021-10-27&to=2021-10-27"

enum class Screens {
    HOME,BOOKMARK,DETAILS,SEARCH,POPULAR_NEWS
}

const val NEWS_API_KEY: String = "e6364decaa6e43f9b673919a635f636b"

const val NEWS_LINK: String = "NEWS_LINK_NAME"



fun showSnackMessage(ctx: Context, @StringRes string: Int, view: View) {
    view.snackbar(string).setActionTextColor(ContextCompat.getColor(ctx, R.color.white))
        .view.setBackgroundColor(ContextCompat.getColor(ctx, R.color.colorRed))
}

fun showSnackMessageError(ctx: Context, string: String, view: View) {
    view.snackbar(string).setActionTextColor(ContextCompat.getColor(ctx, R.color.white))
        .view.setBackgroundColor(ContextCompat.getColor(ctx, R.color.colorRed))
}

/*fun showSnackMessage(ctx: Context, string: String, view: View) {
    view.snackbar(string).setActionTextColor(ContextCompat.getColor(ctx, R.color.white))
        .view.setBackgroundColor(ContextCompat.getColor(ctx, R.color.purple_500))
}*/



