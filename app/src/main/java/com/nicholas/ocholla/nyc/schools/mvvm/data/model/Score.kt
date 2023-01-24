package com.nicholas.ocholla.nyc.schools.mvvm.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Score data class.
 */
data class Score(
    @SerializedName("dbn")
    var databaseName: String,
    @SerializedName("school_name")
    var schoolName: String,
    @SerializedName("num_of_sat_test_takers")
    var numberOfSatTestTakers: String,
    @SerializedName("sat_critical_reading_avg_score")
    var satCriticalReadingAverageScore: String,
    @SerializedName("sat_math_avg_score")
    var satMathAvgScore: String,
    @SerializedName("sat_writing_avg_score")
    var satWritingAverageScore: String
) : Serializable
