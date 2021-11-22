package com.example.demonews.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demonews.R
import com.example.demonews.databinding.LayoutSingleNewsBinding
import com.example.demonews.interfaces.OnBookmarkClickListener
import com.example.demonews.interfaces.OnPopularNewsClickListener
import com.example.demonews.model.entity.Article
import kotlin.collections.ArrayList

class PopularNewsAdapter() : RecyclerView.Adapter<PopularNewsAdapter.ViewHolder>() {

    private lateinit var onPopularNewsClickListener: OnPopularNewsClickListener
    private lateinit var onBookmarkClickListener: OnBookmarkClickListener

//    private var arrayList = mutableListOf<Article>()
//
//    fun setMovieList(movies: List<Article>) {
//        this.arrayList = movies.toMutableList()
//        notifyDataSetChanged()
//    }

    private val differCallBack=object :DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem==newItem
        }

    }

    val differ =AsyncListDiffer(this,differCallBack)



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val article= differ.currentList[position]

        holder.itemView.apply {


            try {
                Glide.with(this)
                        .load(article.urlToImage)
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder)
                        .into(holder.binding.ivSingleImage)

            } catch (e: Exception) {
                e.printStackTrace()
            }

            holder.binding.tvNewsHead.text = article.title
            holder.binding.tvNewsDesc.text = article.description
            holder.binding.tvSource.text = article.source.name

            holder.binding.ivBookmark.setOnClickListener {
                onBookmarkClickListener.onBookmarkClicked(
                        article.title,
                        article.description,
                        article.urlToImage,
                        article.source.name,
                        article.url
                )
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LayoutSingleNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
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
                onPopularNewsClickListener.onNewsItemClick(differ.currentList[layoutPosition].url)
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