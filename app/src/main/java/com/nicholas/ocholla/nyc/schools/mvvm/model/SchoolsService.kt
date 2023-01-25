package com.nicholas.ocholla.nyc.schools.mvvm.model

//import com.nicholas.ocholla.nyc.schools.mvvm.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class SchoolsService {

    @Inject
    lateinit var api: SchoolsApi

    init {
        //DaggerApiComponent.create().inject(this)
    }

    fun getSchools(): Single<List<School>> {
        return api.getSchools()
    }

}