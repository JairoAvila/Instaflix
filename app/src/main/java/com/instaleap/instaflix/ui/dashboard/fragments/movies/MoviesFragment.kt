package com.instaleap.instaflix.ui.dashboard.fragments.movies

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.instaleap.instaflix.data.local.model.Movie
import com.instaleap.instaflix.databinding.MoviesFragmentBinding
import com.instaleap.instaflix.utils.RecyclerViewLoadMoreScroll
import com.instaleap.instaflix.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private val viewmodel: MoviesViewModel by viewModel()

    private var _binding: MoviesFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var mAdapter: MoviesAdapter
    var moviesList: List<Movie> = listOf()
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    lateinit var scrollListener: RecyclerViewLoadMoreScroll

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpViewModelObserver()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewmodel.getMovies(page)
    }

    private fun setUpRecyclerView() {
        mAdapter = MoviesAdapter(moviesList.toMutableList())
        mAdapter.notifyDataSetChanged()
        mLayoutManager = GridLayoutManager(requireContext(), 3)

        binding.rvMovies.apply {
            layoutManager = mLayoutManager
            setHasFixedSize(true)
            adapter = mAdapter
            isNestedScrollingEnabled = true
        }

        scrollListener = RecyclerViewLoadMoreScroll(mLayoutManager as GridLayoutManager)

        scrollListener.setOnLoadMoreListener(object : RecyclerViewLoadMoreScroll.OnLoadMoreListener {
            override fun onLoadMore() {
                loadMoreData()
            }

        })

        binding.rvMovies.addOnScrollListener(scrollListener)
    }

    private fun loadMoreData() {
        Handler(Looper.getMainLooper()).postDelayed({
            page += 1
            viewmodel.getMovies(page)
        }, 1000)
    }

    private fun setUpViewModelObserver() {
        viewmodel.movies.observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    response.data?.let {
                        mAdapter.addData(it.toMutableList())
                        scrollListener.setLoaded()
                    }
                    binding.rvMovies.post { mAdapter.notifyDataSetChanged() }
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> if (page == 1) binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    companion object {
        var page = 1

        fun newInstance() = MoviesFragment()
    }

}