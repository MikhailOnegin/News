package com.example.news.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.presentation.adapters.recyclers.NewsChannelsAdapter
import com.example.news.databinding.NewsChannelsFragmentBinding
import com.example.news.domain.pojo.NewsChannel
import com.example.news.domain.pojo.Source
import com.example.news.presentation.viewModels.NewsChannelViewModel
import com.example.news.presentation.viewModels.SourceViewModel

class NewsChannelsFragment : Fragment() {

    private lateinit var fragmentBinding: NewsChannelsFragmentBinding

    private lateinit var viewModelSource: SourceViewModel
    private lateinit var viewModelNewsChannel: NewsChannelViewModel

    private lateinit var liveDataSource: LiveData<Source>

    private val adapter = NewsChannelsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_channels_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = NewsChannelsFragmentBinding.bind(view)
        fragmentBinding = binding

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelSource = ViewModelProvider(this)[SourceViewModel::class.java]
        viewModelNewsChannel = ViewModelProvider(this)[NewsChannelViewModel::class.java]

        liveDataSource = viewModelSource.getLiveDataSource()

        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        fragmentBinding.recyclerViewChannelsFragment.layoutManager = layoutManager
        fragmentBinding.recyclerViewChannelsFragment.adapter = adapter

        viewModelSource.loadSource()
        adapter.onClick = object : NewsChannelsAdapter.OnClick {
            override fun onClickSaveChannelToDb(item: NewsChannel) {
                viewModelNewsChannel.insertToDb(item)
            }
        }

        liveDataSource.observe(viewLifecycleOwner, Observer {
            val channels = it.sources
            if (channels != null) {
                adapter.addChannels(channels)
                Log.i("eeee", "channel size ${channels.size}")
            }
        })
    }
}