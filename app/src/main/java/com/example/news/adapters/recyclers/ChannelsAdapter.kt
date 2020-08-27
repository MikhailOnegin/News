package com.example.news.adapters.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.pojo.source.NewsChannel
import kotlinx.android.synthetic.main.channel_item.view.*

class ChannelsAdapter : RecyclerView.Adapter<ChannelsAdapter.ViewHolder>() {

    var channels = arrayListOf<NewsChannel>()
    var onClick: OnClick? = null
    fun addChannels(channels: List<NewsChannel>) {
        this.channels.addAll(channels)
        notifyDataSetChanged()
    }

    interface OnClick {
        fun onClickSaveChannelToDb(channel: NewsChannel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.channel_item, parent, false)
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
                }
            }

        }
    }
}