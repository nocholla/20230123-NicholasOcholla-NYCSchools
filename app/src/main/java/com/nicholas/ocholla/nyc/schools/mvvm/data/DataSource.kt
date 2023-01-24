package com.nicholas.ocholla.nyc.schools.mvvm.data

import com.nicholas.ocholla.nyc.schools.mvvm.data.model.Schools
import com.nicholas.ocholla.nyc.schools.mvvm.data.model.Scores

/**
 * Main entry point for accessing data.
 */
interface DataSource {

    interface GetSchoolsCallback {
        fun onOperationComplete(schools: Schools)
        fun onOperationFailed(t: Throwable? = Throwable())
    }

    interface GetScoresCallback {
        fun onOperationComplete(scores: Scores)
        fun onOperationFailed(t: Throwable? = Throwable())
    }

    fun getSchools(page: Int = 1, callback: GetSchoolsCallback)

    fun getScores(page: Int = 1, callback: GetScoresCallback)

}