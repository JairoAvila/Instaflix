package com.instaleap.instaflix.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.instaleap.instaflix.R
import com.instaleap.instaflix.databinding.ActivityDashboardBinding
import com.instaleap.instaflix.ui.dashboard.fragments.movies.MoviesFragment
import com.instaleap.instaflix.ui.dashboard.fragments.tvseries.TvSeriesFragment

class DashboardActivity : AppCompatActivity() {

    private var selectedFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.page_1 -> {
                    selectedFragment = MoviesFragment.newInstance()
                    selectedFragment?.let { openFragment(it) }
                    true
                }
                R.id.page_2 -> {
                    selectedFragment = TvSeriesFragment.newInstance()
                    selectedFragment?.let { openFragment(it) }
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigationView.selectedItemId = R.id.page_1

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}