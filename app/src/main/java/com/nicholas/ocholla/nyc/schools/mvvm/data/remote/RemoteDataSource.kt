package com.nicholas.ocholla.nyc.schools.mvvm.data.remote

import com.nicholas.ocholla.nyc.schools.mvvm.data.DataSource
import com.nicholas.ocholla.nyc.schools.mvvm.data.model.Schools
import com.nicholas.ocholla.nyc.schools.mvvm.data.model.Scores
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Implementation of the data source that interact with server.
 */
class RemoteDataSource(private val apiService: ApiService) : DataSource {

    companion object {

        internal const val BASE_URL = "https://data.cityofnewyork.us/"
        private const val PAGE = "page"
        private const val SORT = "sort_by"

        private var sINSTANCE: RemoteDataSource? = null


        /**
         * Returns the single instance of this class, creating it if necessary.
         */
        @JvmStatic
        fun getInstance(apiService: ApiService) =
            sINSTANCE ?: synchronized(RemoteDataSource::class.java) {
                sINSTANCE ?: RemoteDataSource(apiService)
                    .also { sINSTANCE = it }
            }


        @JvmStatic
        fun destroyInstance() {
            sINSTANCE = null
        }
    }

    override fun getSchools(page: Int, callback: DataSource.GetSchoolsCallback) {
        val queryMap = HashMap<String, String>()

        queryMap[PAGE] = page.toString()

        val call = apiService.getSchools(queryMap);
        call.enqueue(object : Callback<Schools> {
            override fun onFailure(call: Call<Schools>?, t: Throwable?) {
                callback.onOperationFailed(t);
            }

            override fun onResponse(call: Call<Schools>?, response: Response<Schools>?) {
                response?.run {
                    if (isSuccessful) {
                        response.body()?.let { callback.onOperationComplete(it) }
                    } else {
                        callback.onOperationFailed()
                    }
                }
            }
        });
    }

    override fun getScores(page: Int, callback: DataSource.GetScoresCallback) {
        val queryMap = HashMap<String, String>()

        queryMap[PAGE] = page.toString()

        val call = apiService.getScores(queryMap);
        call.enqueue(object : Callback<Scores> {
            override fun onFailure(call: Call<Scores>?, t: Throwable?) {
                callback.onOperationFailed(t);
            }

            override fun onResponse(call: Call<Scores>?, response: Response<Scores>?) {
                response?.run {
                    if (isSuccessful) {
                        response.body()?.let { callback.onOperationComplete(it) }
                    } else {
                        callback.onOperationFailed()
                    }
                }
            }
        });
    }

}
