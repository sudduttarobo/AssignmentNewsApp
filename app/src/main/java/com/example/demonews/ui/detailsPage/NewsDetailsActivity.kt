package com.example.demonews.ui.detailsPage

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.webkit.*
import androidx.databinding.DataBindingUtil
import com.example.demonews.R
import com.example.demonews.databinding.ActivityNewsDetailsBinding
import com.example.demonews.utility.NEWS_LINK

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailsBinding

    private var mdownX = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_news_details)

        initWebView()

        val newsLink:String = intent.getStringExtra(NEWS_LINK).toString()

        binding.tvUrlName.text=newsLink

        binding.webView.loadUrl(newsLink)
    }

    @SuppressLint("ClickableViewAccessibility", "SetJavaScriptEnabled")
    private fun initWebView() {
        binding.webView.webChromeClient = MyWebChromeClient(this)
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(
                view: WebView,
                url: String,
                favicon: Bitmap?
            ) {
                super.onPageStarted(view, url, favicon)
                binding.progressBar.visibility = View.VISIBLE
                invalidateOptionsMenu()
            }


            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                binding.webView.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
                invalidateOptionsMenu()
            }

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                super.onReceivedError(view, request, error)
                binding.progressBar.visibility = View.GONE
                invalidateOptionsMenu()
            }
        }
        binding.webView.clearCache(true)
        binding.webView.clearHistory()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.isHorizontalScrollBarEnabled = false
        binding.webView.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.pointerCount > 1) {
                //Multi touch detected
                return@OnTouchListener true
            }
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {

                    // save the x
                    mdownX = event.x
                }
                MotionEvent.ACTION_MOVE, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {

                    // set x so that it doesn't move
                    event.setLocation(mdownX, event.y)
                }
            }
            false
        })
    }

    private class MyWebChromeClient(var context: Context) :
        WebChromeClient()
}