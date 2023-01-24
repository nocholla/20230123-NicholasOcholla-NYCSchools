package com.nicholas.ocholla.nyc.schools.mvvm.data.remote

import com.nicholas.ocholla.nyc.schools.mvvm.data.model.Schools
import com.nicholas.ocholla.nyc.schools.mvvm.data.model.Scores
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Api call for retrofit.
 */
interface ApiService {

    @GET("resource/s3k6-pzi2.json")
    fun getSchools(@QueryMap queryMap: Map<String, String>): Call<Schools>

    @GET("resource/f9bf-2cp4.json")
    fun getScores(@QueryMap queryMap: Map<String, String>): Call<Scores>

}