package com.example.news.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.adapters.recyclers.ChannelsAdapter
import com.example.news.databinding.ChannelsFragmentBinding
import com.example.news.pojo.source.NewsChannel
import com.example.news.viewModels.ChannelsViewModel

class ChannelsFragment : Fragment() {

    private lateinit var fragmentBinding: ChannelsFragmentBinding
    private lateinit var viewModel: ChannelsViewModel
    private val adapter = ChannelsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.channels_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ChannelsFragmentBinding.bind(view)
        fragmentBinding = binding

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        fragmentBinding.recyclerViewChannelsFragment.layoutManager = layoutManager
        fragmentBinding.recyclerViewChannelsFragment.adapter = adapter
        viewModel = ViewModelProvider(this)[ChannelsViewModel::class.java]
        viewModel.loadSource()
        adapter.onClick = object : ChannelsAdapter.OnClick {
            override fun onClickSaveChannelToDb(channel: NewsChannel) {
                viewModel.insertToDb(channel)
            }
        }
        viewModel.liveDataSource.observe(viewLifecycleOwner, Observer {
            val channels = it.sources
            if (channels != null) {
                adapter.addChannels(channels)
                Log.i("eeee", "channel size ${channels.size}")
            }

        })


    }
}