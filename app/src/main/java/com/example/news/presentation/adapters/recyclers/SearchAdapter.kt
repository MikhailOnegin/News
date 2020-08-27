package com.example.news.presentation.adapters.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.domain.pojo.Article
import kotlinx.android.synthetic.main.search_item.view.*

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var articles = arrayListOf<Article>()

    fun addEverything(everything: List<Article>) {
        this.articles.addAll(everything)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setEverything(items: List<Article>) {
        this.articles = items.toCollection(ArrayList())
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tVTitle = itemView.textView_search_item_title
        private val tVDescription = itemView.textView_search_item_description
        private val iVLogo = itemView.imageView_search_item_logo

        fun bind(item: Article) {
            articles.let {
                tVTitle.text = articles[adapterPosition].title
                tVDescription.text = articles[adapterPosition].description
                val url = item.urlToImage
                Glide.with(itemView.context).load(url).placeholder(R.drawable.not_image)
                    .centerCrop().into(iVLogo)

            }
        }
    }
}