package com.nicholas.ocholla.nyc.schools.mvvm.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class for list of Schools.
 */
data class Schools constructor(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var Schools: List<School>
) {

}