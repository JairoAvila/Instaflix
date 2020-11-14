package com.instaleap.instaflix.di

import com.instaleap.instaflix.BuildConfig
import com.instaleap.instaflix.data.remote.MovieApi
import com.instaleap.instaflix.utils.ResponseHandler
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_URL = "https://api.themoviedb.org/3/"

val networkModule = module {
    factory { provideLoggingInterceptor() }
    factory { provideAuthInterceptor }
    factory { provideOkHttpClient(get(), get()) }
    single { provideRetrofit(get()) }
    factory { provideMovieApi(get()) }
    factory { ResponseHandler() }
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

val provideAuthInterceptor: Interceptor = object : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}

fun provideOkHttpClient(authInterceptor: Interceptor, interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().apply {
        addInterceptor(authInterceptor)
        addInterceptor(interceptor)
    }.build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().apply {
        baseUrl(API_URL)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
    }.build()
}

fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)