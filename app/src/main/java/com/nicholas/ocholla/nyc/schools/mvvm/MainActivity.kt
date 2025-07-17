package com.nicholas.ocholla.nyc.schools.mvvm


import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholas.ocholla.nyc.schools.mvvm.view.SchoolListAdapter
import com.nicholas.ocholla.nyc.schools.mvvm.viewmodel.ListViewModel
import com.nicholas.ocholla.nyc.schools.mvvm.databinding.ActivityMainBinding
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    // View Binding instance
    private lateinit var binding: ActivityMainBinding // Declare binding

    // Orientation
    private val isTablet: Boolean
        get() = (
                this.resources.configuration.screenLayout and
                        Configuration.SCREENLAYOUT_SIZE_MASK >=
                        Configuration.SCREENLAYOUT_SIZE_LARGE)

    private val viewModel: ListViewModel by viewModels()
    private val schoolsAdapter = SchoolListAdapter(this, arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide Toolbar (if using AppCompatActivity's default toolbar)
        supportActionBar?.hide()

        // Orientation Check
        if (!isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        viewModel.refresh()

        // Access views via binding
        binding.schoolsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = schoolsAdapter
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.schools.observe(this, Observer { schools ->
            schools?.let {
                binding.schoolsList.visibility = View.VISIBLE
                schoolsAdapter.updateSchools(it)
            }
        })

        viewModel.schoolLoadError.observe(this, Observer { isError ->
            isError?.let { binding.listError.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    binding.listError.visibility = View.GONE
                    binding.schoolsList.visibility = View.GONE
                }
            }
        })
    }

    companion object {
        val TAG = "MainActivity"

        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}