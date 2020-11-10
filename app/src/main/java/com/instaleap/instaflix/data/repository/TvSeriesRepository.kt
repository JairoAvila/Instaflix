package com.instaleap.instaflix.data.repository

import com.instaleap.instaflix.data.datasource.LocalDataSource
import com.instaleap.instaflix.data.datasource.RemoteDataSource

class TvSeriesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
}