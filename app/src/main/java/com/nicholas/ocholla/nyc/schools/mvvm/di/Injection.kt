package com.nicholas.ocholla.nyc.schools.mvvm.di

import android.content.Context
import com.nicholas.ocholla.nyc.schools.mvvm.data.Repository
import com.nicholas.ocholla.nyc.schools.mvvm.data.remote.ApiService
import com.nicholas.ocholla.nyc.schools.mvvm.data.remote.RemoteDataSource
import com.nicholas.ocholla.nyc.schools.mvvm.data.remote.RemoteDataSource.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Inject the dependency to create Repository. Useful for testing.
 */
object Injection {

    fun provideTasksRepository(context: Context): Repository {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        return Repository.getInstance(RemoteDataSource.getInstance(apiService))
    }

}