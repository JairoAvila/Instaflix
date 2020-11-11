package com.instaleap.instaflix.ui.dashboard.fragments.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.instaleap.instaflix.R
import com.instaleap.instaflix.utils.Status
import kotlinx.android.synthetic.main.movies_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private val viewmodel: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.getMovie.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Status.SUCCESS -> view.progressBar.visibility = View.GONE
                Status.ERROR -> view.progressBar.visibility = View.GONE
                Status.LOADING -> view.progressBar.visibility = View.VISIBLE
            }
        })
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }

}