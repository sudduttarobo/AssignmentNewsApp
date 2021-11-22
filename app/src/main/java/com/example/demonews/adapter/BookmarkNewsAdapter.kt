package com.example.demonews.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demonews.R
import com.example.demonews.databinding.LayoutSingleNewsBinding
import com.example.demonews.interfaces.OnBookmarkClickListener
import com.example.demonews.interfaces.OnPopularNewsClickListener
import com.example.demonews.model.entity.Article
import java.util.*

class BookmarkNewsAdapter (
    private val context: Context,
    private val arrayList: ArrayList<Article>
) :
    RecyclerView.Adapter<BookmarkNewsAdapter.ViewHolder>() {

    private lateinit var onPopularNewsClickListener: OnPopularNewsClickListener
    private lateinit var onBookmarkClickListener: OnBookmarkClickListener


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        try {
            Glide.with(context)
                .load(arrayList[position].urlToImage)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(holder.binding.ivSingleImage)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        holder.binding.tvNewsHead.text = arrayList[position].title
        holder.binding.tvNewsDesc.text = arrayList[position].description
        holder.binding.tvSource.text = arrayList[position].source.name

        holder.binding.ivBookmark.setOnClickListener {
            onBookmarkClickListener.onBookmarkClicked(
                arrayList[position].title,
                arrayList[position].description,
                arrayList[position].urlToImage,
                arrayList[position].source.name,
                arrayList[position].url
            )
        }


    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LayoutSingleNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_single_news, parent, false
        )

        return ViewHolder(binding)
    }

    inner class ViewHolder(binding_: LayoutSingleNewsBinding) :
        RecyclerView.ViewHolder(binding_.root), View.OnClickListener {
        val binding: LayoutSingleNewsBinding = binding_

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
              //  onPopularNewsClickListener.onNewsItemClick(arrayList[layoutPosition].urlNews)
            }
        }

    }

    fun setOnNewsItemClickListener(onPopularNewsClickListener: OnPopularNewsClickListener) {
        this.onPopularNewsClickListener = onPopularNewsClickListener
    }

    fun setOnBookmarkClickListener(onBookmarkClickListener: OnBookmarkClickListener) {
        this.onBookmarkClickListener = onBookmarkClickListener
    }


}