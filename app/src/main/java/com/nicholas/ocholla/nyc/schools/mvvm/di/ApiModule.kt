package com.nicholas.ocholla.nyc.schools.mvvm.di

import com.nicholas.ocholla.nyc.schools.mvvm.data.api.SchoolsApi
import com.nicholas.ocholla.nyc.schools.mvvm.data.service.SchoolsService
import com.nicholas.ocholla.nyc.schools.mvvm.data.api.ScoresApi
import com.nicholas.ocholla.nyc.schools.mvvm.data.service.ScoresService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://data.cityofnewyork.us/"

    @Provides
    fun provideSchoolsApi(): SchoolsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SchoolsApi::class.java)
    }

    @Provides
    fun provideScoresApi(): ScoresApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ScoresApi::class.java)
    }

    @Provides
    fun provideSchoolsService(): SchoolsService {
        return SchoolsService()
    }

    @Provides
    fun provideScoresService(): ScoresService {
        return ScoresService()
    }

}