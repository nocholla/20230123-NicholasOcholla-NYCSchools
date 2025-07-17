package com.nicholas.ocholla.nyc.schools.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nicholas.ocholla.nyc.schools.mvvm.di.DaggerApiComponent
import com.nicholas.ocholla.nyc.schools.mvvm.model.School
import com.nicholas.ocholla.nyc.schools.mvvm.model.SchoolsService
import com.nicholas.ocholla.nyc.schools.mvvm.model.Score
import com.nicholas.ocholla.nyc.schools.mvvm.model.ScoresService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel: ViewModel() {

    @Inject
    lateinit var schoolsService: SchoolsService

    @Inject
    lateinit var scoresService: ScoresService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val schools = MutableLiveData<List<School>>()
    val scores = MutableLiveData<List<Score>>()
    val schoolLoadError = MutableLiveData<Boolean>()
    val scoreLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchSchools()
        fetchScores()
    }

    private fun fetchSchools() {
        loading.value = true
        disposable.add(
            schoolsService.getSchools()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<School>>() {
                    override fun onSuccess(value: List<School>) {
                        schools.value = value
                        schoolLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        schoolLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun fetchScores() {
        loading.value = true
        disposable.add(
            scoresService.getScores()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Score>>() {
                    override fun onSuccess(value: List<Score>) {
                        scores.value = value
                        scoreLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        scoreLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}