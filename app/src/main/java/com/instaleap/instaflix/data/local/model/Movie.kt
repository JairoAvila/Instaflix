package com.instaleap.instaflix.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie (
    @PrimaryKey var id: Int,
    var page : Int,
    var title: String,
    var genre: List<Int>,
    var poster: String
)