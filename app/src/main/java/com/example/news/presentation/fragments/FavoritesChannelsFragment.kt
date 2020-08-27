package com.example.news.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.presentation.adapters.recyclers.FavoriteChannelsAdapter
import com.example.news.databinding.FavoriteChannelsFragmentBinding
import com.example.news.domain.pojo.NewsChannel
import com.example.news.domain.pojo.Source
import com.example.news.presentation.viewModels.NewsChannelViewModel
import com.example.news.presentation.viewModels.SourceViewModel

class FavoritesChannelsFragment : Fragment() {

    private lateinit var fragmentBinding: FavoriteChannelsFragmentBinding

    private lateinit var viewModelSource: SourceViewModel
    private lateinit var viewModelNewsChannel:NewsChannelViewModel

    private lateinit var liveDataSource:LiveData<Source>
    private lateinit var liveDataNewsChannels:LiveData<List<NewsChannel>>

    private val adapter = FavoriteChannelsAdapter()
    private lateinit var navC: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_channels_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FavoriteChannelsFragmentBinding.bind(view)
        fragmentBinding = binding

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelSource = ViewModelProvider(this)[SourceViewModel::class.java]
        viewModelNewsChannel = ViewModelProvider(this)[NewsChannelViewModel::class.java]

        liveDataSource = viewModelSource.getLiveDataSource()
        liveDataNewsChannels = viewModelNewsChannel.getLiveDataChannels()

        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        navC = findNavController()
        fragmentBinding.recyclerViewFavoriteChannelsFragment.layoutManager = layoutManager
        fragmentBinding.recyclerViewFavoriteChannelsFragment.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val item = adapter.channels[position]
                    viewModelNewsChannel.deleteFromDb(item)
                    adapter.remove(position)
                }
            }
        )

        itemTouchHelper.attachToRecyclerView(fragmentBinding.recyclerViewFavoriteChannelsFragment)

        liveDataNewsChannels.observe(viewLifecycleOwner, Observer {
            adapter.setChannels(it)
        })

        fragmentBinding.floatingActionButtonFavoriteChennelsNews.setOnClickListener {
            navC.navigate(R.id.action_favorite_channels_to_news_from_favorite_channels)
        }
    }
}