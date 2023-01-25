package com.nicholas.ocholla.nyc.schools.mvvm.di

import com.nicholas.ocholla.nyc.schools.mvvm.model.SchoolsService
import com.nicholas.ocholla.nyc.schools.mvvm.model.ScoresService
import com.nicholas.ocholla.nyc.schools.mvvm.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: SchoolsService)

    fun inject(service: ScoresService)

    fun inject(viewModel: ListViewModel)

}