package com.example.news.presentation.adapters.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.domain.pojo.NewsChannel
import kotlinx.android.synthetic.main.news_channel_item.view.*

class NewsChannelsAdapter : RecyclerView.Adapter<NewsChannelsAdapter.ViewHolder>() {

    var channels = arrayListOf<NewsChannel>()

    var onClick: OnClick? = null

    var anim: Animation? = null

    fun addChannels(channels: List<NewsChannel>) {
        this.channels.addAll(channels)
        notifyDataSetChanged()
    }

    interface OnClick {
        fun onClickSaveChannelToDb(item: NewsChannel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        anim = AnimationUtils.loadAnimation(parent.context, R.anim.loading)
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.news_channel_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return channels.size
    }

    fun remove(item: NewsChannel) {
        channels.remove(item)
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        channels.removeAt(position)
        notifyDataSetChanged()
    }

    fun setChannels(items: List<NewsChannel>) {
        this.channels = items.toCollection(ArrayList())
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(channels)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tVTitle = itemView.textView_channel_item_title
        private val tVDescription = itemView.textView_channel_item_description
        private val iVFavorite = itemView.imageView_channels_item_favorite

        fun bind(channels: List<NewsChannel>) {
            channels.let {
                tVTitle.text = channels[adapterPosition].name
                tVDescription.text = channels[adapterPosition].description
                iVFavorite.setOnClickListener {
                    val channel = channels[adapterPosition]
                    onClick?.onClickSaveChannelToDb(channel)
                    anim?.let {
                        iVFavorite.startAnimation(it)
                    }

                }
            }
        }
    }
}