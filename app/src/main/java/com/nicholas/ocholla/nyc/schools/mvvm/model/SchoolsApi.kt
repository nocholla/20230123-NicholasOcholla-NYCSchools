package com.nicholas.ocholla.nyc.schools.mvvm.model

import io.reactivex.Single
import retrofit2.http.GET

interface SchoolsApi {

    @GET("resource/s3k6-pzi2.json")
    fun getSchools(): Single<List<School>>

}