package com.example.news.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.news.R
import com.example.news.databinding.ChannelsFragmentBinding
import com.example.news.databinding.SearchChannelsFragmentBinding
import com.example.news.viewModels.SearchViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchChannelsFragment : Fragment() {

    private lateinit var viewModelSearch:SearchViewModel
    private lateinit var fragmentBinding: SearchChannelsFragmentBinding



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
        val a:Any
        viewModelSearch = ViewModelProvider(this)[SearchViewModel::class.java]
        val o = Observable.just(fragmentBinding.editTextSearchFragmentTitle.text)
        o.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
           Toast.makeText(requireContext(), "Char",Toast.LENGTH_SHORT).show()
        },{})

    }
}