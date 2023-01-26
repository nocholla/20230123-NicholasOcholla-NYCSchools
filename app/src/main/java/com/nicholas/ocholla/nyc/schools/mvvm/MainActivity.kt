package com.nicholas.ocholla.nyc.schools.mvvm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholas.ocholla.nyc.schools.mvvm.view.SchoolListAdapter
import com.nicholas.ocholla.nyc.schools.mvvm.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val schoolsAdapter = SchoolListAdapter(this, arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)[ListViewModel::class.java]
        viewModel.refresh()

        schoolsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = schoolsAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.schools.observe(this, Observer {schools ->
            schools?.let {
                schoolsList.visibility = View.VISIBLE
                schoolsAdapter.updateSchools(it) }
        })

        viewModel.schoolLoadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    schoolsList.visibility = View.GONE
                }
            }
        })
    }

    companion object {
        val TAG = "MainActivity"

        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

}