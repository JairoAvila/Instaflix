package com.instaleap.instaflix.ui.dashboard.fragments.movies

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.instaleap.instaflix.R
import com.instaleap.instaflix.data.local.model.Movie
import com.instaleap.instaflix.utils.Constant
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.progress_loading.view.*

class MoviesAdapter(private var movieList: MutableList<Movie?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var mcontext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mcontext = parent.context
        return if (viewType == Constant.VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(mcontext).inflate(R.layout.progress_loading, parent, false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                view.progressbar.indeterminateDrawable.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
            } else {
                view.progressbar.indeterminateDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
            }
            LoadingViewHolder(view)
        }
    }

    override fun getItemCount(): Int = movieList.size

    override fun getItemViewType(position: Int): Int {
        return if (movieList[position] == null) {
            Constant.VIEW_TYPE_LOADING
        } else {
            Constant.VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == Constant.VIEW_TYPE_ITEM) {
            holder.itemView.tv_title.text = movieList[position]?.title
            holder.itemView.iv_poster.load("${TMDB_IMAGE_PATH}${movieList[position]?.poster}")
        }
    }

    fun addData(dataViews: MutableList<Movie>) {
        this.movieList.addAll(dataViews)
        notifyDataSetChanged()
    }

    fun cleanData(){
        this.movieList.clear()
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): Movie? {
        return movieList[position]
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    enum class MovieType(val id: Int) { POPULAR(0), WAR(10752), ROMANCE(10749)}

    companion object {
        private const val TMDB_IMAGE_PATH = "https://image.tmdb.org/t/p/w500"
    }
}