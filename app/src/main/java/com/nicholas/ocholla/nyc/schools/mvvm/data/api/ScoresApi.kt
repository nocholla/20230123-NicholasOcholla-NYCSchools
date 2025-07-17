package com.nicholas.ocholla.nyc.schools.mvvm.data.api

import com.nicholas.ocholla.nyc.schools.mvvm.model.Score
import io.reactivex.Single
import retrofit2.http.GET

interface ScoresApi {

    @GET("resource/f9bf-2cp4.json")
    fun getScores(): Single<List<Score>>

}