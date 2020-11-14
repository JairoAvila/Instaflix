package com.instaleap.instaflix.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@ExperimentalCoroutinesApi
abstract class NetworkBoundResource<ResultType, RequestType> {

    fun asFlow(): Flow<Resource<ResultType>> {
        return flow {
            emit(Resource.loading())
            val dbResult = loadFromDb().first()
            if (shouldFetch(dbResult)) {
                try {
                    emit(Resource.loading())
                    val apiResponse = fetchFromNetwork()
                    saveNetworkResult(processResponse(apiResponse.data!!))
                    emitAll(loadFromDb().map { Resource.success(it) })
                } catch (e: Exception) {
                    emit(Resource.error(e.message))
                }
            } else {
                emitAll(loadFromDb().map { Resource.success(it) })
            }
        }
    }

    @WorkerThread
    protected abstract fun processResponse(response: RequestType): ResultType

    @WorkerThread
    protected abstract suspend fun saveNetworkResult(items: ResultType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flow<ResultType>

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): Resource<RequestType>
}
