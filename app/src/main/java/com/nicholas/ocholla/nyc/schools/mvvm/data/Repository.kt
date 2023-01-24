package com.nicholas.ocholla.nyc.schools.mvvm.data

/**
 * Implementation of DataSource. Can work like a wrapper for all data source ( local or remote).
 */
class Repository(private val remoteDataSource: DataSource) : DataSource {

    companion object {

        private var sINSTANCE: Repository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.

         * @param remoteDataSource the backend data source
         *
         * @return the [Repository] instance
         */
        @JvmStatic
        fun getInstance(remoteDataSource: DataSource) =
            sINSTANCE ?: synchronized(Repository::class.java) {
                sINSTANCE ?: Repository(remoteDataSource)
                    .also { sINSTANCE = it }
            }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            sINSTANCE = null
        }
    }

    override fun getSchools(page: Int, callback: DataSource.GetSchoolsCallback) {
        remoteDataSource.getSchools(page, callback)
    }

    override fun getScores(page: Int, callback: DataSource.GetScoresCallback) {
        remoteDataSource.getScores(page, callback)
    }

}
