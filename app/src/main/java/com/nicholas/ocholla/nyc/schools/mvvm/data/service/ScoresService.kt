package com.nicholas.ocholla.nyc.schools.mvvm.data.service

import com.nicholas.ocholla.nyc.schools.mvvm.data.api.ScoresApi
import com.nicholas.ocholla.nyc.schools.mvvm.di.DaggerApiComponent
import com.nicholas.ocholla.nyc.schools.mvvm.model.Score
import io.reactivex.Single
import javax.inject.Inject

class ScoresService {

    @Inject
    lateinit var api: ScoresApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getScores(): Single<List<Score>> {
        return api.getScores()
    }

}