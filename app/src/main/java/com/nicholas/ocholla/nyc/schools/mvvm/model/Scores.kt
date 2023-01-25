package com.nicholas.ocholla.nyc.schools.mvvm.model

import com.google.gson.annotations.SerializedName

/**
 * Data class for list of Scores.
 */
data class Scores constructor(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var Scores: List<Score>
) {

}