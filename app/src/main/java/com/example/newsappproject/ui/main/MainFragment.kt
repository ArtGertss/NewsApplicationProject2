package com.example.newsappproject.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappproject.R
import com.example.newsappproject.databinding.FragmentDetailsBinding
import com.example.newsappproject.databinding.FragmentMainBinding
import com.example.newsappproject.ui.adapters.NewsAdapter
import com.example.newsappproject.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.newsLiveData.observe(viewLifecycleOwner){ responce ->
            when(responce){
                is Resource.Success ->{

                    progress_bar.visibility = View.INVISIBLE
                    responce.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Error->{
                    progress_bar.visibility = View.INVISIBLE
                    responce.data?.let {
                        Log.d("checkData", "MainFragment: error: ${it}")
                    }
                }
                is Resource.Loading ->{
                    progress_bar.visibility = View.VISIBLE
                }
            }
        }

    }
    private fun initAdapter(){
        newsAdapter = NewsAdapter()
        recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}