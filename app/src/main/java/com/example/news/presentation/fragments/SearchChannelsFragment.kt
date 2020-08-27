package com.example.news.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.SearchChannelsFragmentBinding
import com.example.news.presentation.adapters.recyclers.SearchAdapter
import com.example.news.presentation.viewModels.SearchViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchChannelsFragment : Fragment() {

    private lateinit var fragmentBinding: SearchChannelsFragmentBinding

    private lateinit var viewModelSearch: SearchViewModel

    private val adapter = SearchAdapter()
    private lateinit var navC: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_channels_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = SearchChannelsFragmentBinding.bind(view)
        fragmentBinding = binding

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelSearch = ViewModelProvider(this)[SearchViewModel::class.java]

        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        fragmentBinding.recyclerViewSearchFragment.layoutManager = layoutManager
        fragmentBinding.recyclerViewSearchFragment.adapter = adapter
        fragmentBinding.editTextSearchFragmentTitle.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val s = fragmentBinding.editTextSearchFragmentTitle.text.toString()
                if (s.isNotEmpty()) {
                    viewModelSearch.loadSource(s)
                }
            }
        })

        viewModelSearch.getLiveDataEverything().observe(viewLifecycleOwner, Observer {
            if (it.articles != null) {
                adapter.setEverything(it.articles.toList())
            }
        })
    }
}