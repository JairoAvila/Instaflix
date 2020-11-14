package com.instaleap.instaflix.ui.dashboard.fragments.movies

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
        setUpOnClickListener()
        setUpViewModelObserver()
    }

    private fun setUpOnClickListener() {

        binding.tvPopular.setOnClickListener {
            binding.tvPopular.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark))
            binding.tvWar.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            binding.tvRomance.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            getMovies(MoviesAdapter.MovieType.POPULAR)
        }

        binding.tvWar.setOnClickListener {
            binding.tvPopular.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            binding.tvWar.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark))
            binding.tvRomance.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            getMovies(MoviesAdapter.MovieType.WAR)
        }

        binding.tvRomance.setOnClickListener {
            binding.tvPopular.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            binding.tvRomance.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark))
            binding.tvWar.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            getMovies(MoviesAdapter.MovieType.ROMANCE)
        }

    }

    private fun getMovies(type: MoviesAdapter.MovieType) {
        page = 1
        mAdapter.cleanData()
        movieType = type
        viewmodel.getMovies(page)
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
            nextPage()
        }, 1000)
    }

    private fun nextPage() {
        page += 1
        viewmodel.getMovies(page)
    }

    private fun setUpViewModelObserver() {
        viewmodel.movies.observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    val moviesFilter = mutableListOf<Movie>()
                    response.data?.let {
                        when (movieType) {
                            MoviesAdapter.MovieType.POPULAR -> moviesFilter.addAll(it)
                            MoviesAdapter.MovieType.WAR -> moviesFilter.addAll((it.filter { movie ->  movie.genre.contains(MoviesAdapter.MovieType.WAR.id) }))
                            MoviesAdapter.MovieType.ROMANCE -> moviesFilter.addAll((it.filter { movie ->  movie.genre.contains(MoviesAdapter.MovieType.ROMANCE.id) }))
                        }
                    }
                    mAdapter.addData(moviesFilter)
                    scrollListener.setLoaded()
                    binding.rvMovies.post { mAdapter.notifyDataSetChanged() }
                    if(moviesFilter.size <= 2) nextPage()
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
        var movieType: MoviesAdapter.MovieType = MoviesAdapter.MovieType.POPULAR

        fun newInstance() = MoviesFragment()
    }

}