package com.nicholas.ocholla.nyc.schools.mvvm.di

import com.nicholas.ocholla.nyc.schools.mvvm.data.service.SchoolsService
import com.nicholas.ocholla.nyc.schools.mvvm.data.service.ScoresService
import com.nicholas.ocholla.nyc.schools.mvvm.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: SchoolsService)

    fun inject(service: ScoresService)

    fun inject(viewModel: ListViewModel)

}