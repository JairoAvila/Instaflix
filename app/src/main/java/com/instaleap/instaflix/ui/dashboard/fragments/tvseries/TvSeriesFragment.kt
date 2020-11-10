package com.instaleap.instaflix.ui.dashboard.fragments.tvseries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.instaleap.instaflix.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvSeriesFragment : Fragment() {

    private val viewModel: TvSeriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tv_series_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = TvSeriesFragment()
    }

}