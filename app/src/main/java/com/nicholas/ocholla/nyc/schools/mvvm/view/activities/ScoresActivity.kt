package com.nicholas.ocholla.nyc.schools.mvvm.view.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholas.ocholla.nyc.schools.mvvm.R
import com.nicholas.ocholla.nyc.schools.mvvm.view.ScoreListAdapter
import com.nicholas.ocholla.nyc.schools.mvvm.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_scores.*

class ScoresActivity : AppCompatActivity() {

    // Orientation
    private val isTablet: Boolean
        get() = (this.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE)

    lateinit var viewModel: ListViewModel
    private val scoresAdapter = ScoreListAdapter(this, arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)

        // Orientation Check
        if (!isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        viewModel = ViewModelProviders.of(this)[ListViewModel::class.java]
        viewModel.refresh()

        scoresList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = scoresAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.scores.observe(this, Observer {scores ->
            scores?.let {
                scoresList.visibility = View.VISIBLE
                scoresAdapter.updateScores(it) }
        })

        viewModel.scoreLoadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    scoresList.visibility = View.GONE
                }
            }
        })
    }

    companion object {
        val TAG = "ScoresActivity"

        fun newIntent(context: Context) = Intent(context, ScoresActivity::class.java)
    }

}