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
import com.example.news.presentation.adapters.recyclers.ArticlesAdapter
import com.example.news.databinding.NewsFromFavoriteChannelsBinding
import com.example.news.domain.pojo.Article
import com.example.news.domain.pojo.NewsChannel
import com.example.news.domain.pojo.Source
import com.example.news.domain.pojo.TopHeadlines
import com.example.news.presentation.viewModels.ArticlesViewModel
import com.example.news.presentation.viewModels.NewsChannelViewModel
import com.example.news.presentation.viewModels.SourceViewModel
import com.example.news.presentation.viewModels.TopHeadlinesViewModel

class ArticlesFragment : Fragment() {

    private lateinit var fragmentBinding: NewsFromFavoriteChannelsBinding

    private lateinit var viewModelSource: SourceViewModel
    private lateinit var viewModelTopHeadlines: TopHeadlinesViewModel
    private lateinit var viewModelArticles: ArticlesViewModel
    private lateinit var viewModelNewsChannels: NewsChannelViewModel

    private lateinit var liveDataNewsChannels: LiveData<List<NewsChannel>>
    private lateinit var liveDataSource: LiveData<Source>
    private lateinit var liveDataTopHeadlines: LiveData<TopHeadlines?>
    private lateinit var liveDataArticles: LiveData<List<Article>>

    private val adapter: ArticlesAdapter = ArticlesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_from_favorite_channels, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = NewsFromFavoriteChannelsBinding.bind(view)
        fragmentBinding = binding

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelSource = ViewModelProvider(this)[SourceViewModel::class.java]
        viewModelTopHeadlines = ViewModelProvider(this)[TopHeadlinesViewModel::class.java]
        viewModelArticles = ViewModelProvider(this)[ArticlesViewModel::class.java]
        viewModelNewsChannels = ViewModelProvider(this)[NewsChannelViewModel::class.java]

        liveDataNewsChannels = viewModelNewsChannels.getLiveDataChannels()
        liveDataSource = viewModelSource.getLiveDataSource()
        liveDataTopHeadlines = viewModelTopHeadlines.getLiveDataTopHeadlines()
        liveDataArticles = viewModelArticles.getLiveDataArticlesFromDb()

        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        fragmentBinding.recycelrViiewNewsFromFavoriteChannels.layoutManager = layoutManager
        fragmentBinding.recycelrViiewNewsFromFavoriteChannels.adapter = adapter

        liveDataNewsChannels.observe(viewLifecycleOwner, Observer {
            val id = mutableListOf<String>()
            it.forEach { newsChannel ->
                id.add(newsChannel.id)
            }
            viewModelTopHeadlines.loadTopHeadlines(id)
        })

        liveDataTopHeadlines.observe(viewLifecycleOwner, Observer {
            val articles = it?.articles
            if (articles != null) {
                adapter.setArticles(articles.toList())
                viewModelArticles.deleteAllFromDb()
                viewModelArticles.insertToDb(articles.toList())
            }
        }
        )

        viewModelArticles.getLiveDataArticlesFromDb().observe(viewLifecycleOwner, Observer {
            Log.i("eeee", "articles fromDb size: ${it.size}")
            adapter.setArticles(it)
        })
    }
}