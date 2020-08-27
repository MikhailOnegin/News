package com.example.news.presentation.adapters.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.domain.pojo.Article
import kotlinx.android.synthetic.main.news_from_favorite_channels_item.view.*

class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    var articles = arrayListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_from_favorite_channels_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setArticles(items: List<Article>) {
        articles = items.toCollection(ArrayList())
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = articles[position]
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tVTitle: TextView = itemView.textView_news_from_favorite_channels_item_title
        private val tVDescription: TextView =
            itemView.extView_news_from_favorite_channels_item_description
        private val iVLogo: ImageView = itemView.imageView_news_from_favorite_channels_item_logo

        fun bind(item: Article) {
            tVTitle.text = item.title
            tVDescription.text = item.description
            val url = item.urlToImage
            Glide.with(itemView.context).load(url).placeholder(R.drawable.not_image).centerCrop()
                .into(iVLogo)
        }
    }
}