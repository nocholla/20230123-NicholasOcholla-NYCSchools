package com.nicholas.ocholla.nyc.schools.mvvm.model

import com.nicholas.ocholla.nyc.schools.mvvm.di.DaggerApiComponent
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