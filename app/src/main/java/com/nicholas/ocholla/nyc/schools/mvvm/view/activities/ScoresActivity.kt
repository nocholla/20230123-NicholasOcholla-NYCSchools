package com.nicholas.ocholla.nyc.schools.mvvm.view.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholas.ocholla.nyc.schools.mvvm.view.adapter.ScoreListAdapter
import com.nicholas.ocholla.nyc.schools.mvvm.viewmodel.ListViewModel
import com.nicholas.ocholla.nyc.schools.mvvm.databinding.ActivityScoresBinding
import androidx.activity.viewModels

class ScoresActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScoresBinding // Declare binding

    // Orientation
    private val isTablet: Boolean
        get() = (
                this.resources.configuration.screenLayout and
                        Configuration.SCREENLAYOUT_SIZE_MASK >=
                        Configuration.SCREENLAYOUT_SIZE_LARGE)

    // Use by viewModels() delegate for ViewModel initialization
    private val viewModel: ListViewModel by viewModels()
    private val scoresAdapter = ScoreListAdapter(this, arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide Toolbar
        supportActionBar?.hide()

        // Orientation Check
        if (!isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        binding.ibClose.setOnClickListener {
            finish()
        }

        viewModel.refresh()

        binding.scoresList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = scoresAdapter
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.scores.observe(this, Observer { scores ->
            scores?.let {
                binding.scoresList.visibility = View.VISIBLE
                scoresAdapter.updateScores(it)
            }
        })

        viewModel.scoreLoadError.observe(this, Observer { isError ->
            isError?.let { binding.listError.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    binding.listError.visibility = View.GONE
                    binding.scoresList.visibility = View.GONE
                }
            }
        })
    }

    companion object {
        val TAG = "ScoresActivity"
        fun newIntent(context: Context) = Intent(context, ScoresActivity::class.java)
    }
}