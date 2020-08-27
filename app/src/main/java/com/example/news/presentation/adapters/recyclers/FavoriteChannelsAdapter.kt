package com.example.news.presentation.adapters.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.domain.pojo.NewsChannel
import kotlinx.android.synthetic.main.favorite_channel_item.view.*

class FavoriteChannelsAdapter : RecyclerView.Adapter<FavoriteChannelsAdapter.ViewHolder>() {

    var channels = arrayListOf<NewsChannel>()
    var onClick: OnClick? = null
    fun addChannels(channels: List<NewsChannel>) {
        this.channels.addAll(channels)
        notifyDataSetChanged()
    }


    interface OnClick {
        fun onClickSaveChannelToDb(channel: NewsChannel)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_channel_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return channels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(channels)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tVTitle = itemView.textView_favorite_channel_item_title
        private val tVDescription = itemView.textView_favorite_channel_item_description
        fun bind(channels: List<NewsChannel>) {
            channels.let {
                tVTitle.text = channels[adapterPosition].name
                tVDescription.text = channels[adapterPosition].description
            }
        }
    }
}