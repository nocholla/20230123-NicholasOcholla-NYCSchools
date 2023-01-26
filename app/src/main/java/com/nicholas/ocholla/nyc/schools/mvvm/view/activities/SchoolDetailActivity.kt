package com.nicholas.ocholla.nyc.schools.mvvm.view.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.nicholas.ocholla.nyc.schools.mvvm.R
import com.nicholas.ocholla.nyc.schools.mvvm.util.addDebouncedClickListener
import java.util.*

class SchoolDetailActivity : AppCompatActivity() {

    // Orientation
    private val isTablet: Boolean
        get() = (this.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE)

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_detail)

        // Orientation Check
        if (!isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        // Widgets
        val backBtn = findViewById<ImageView>(R.id.toolBar)
        val schoolName = findViewById<TextView>(R.id.tv_school_name)
        val cardShare = findViewById<CardView>(R.id.card_share)
        val cardSocial = findViewById<CardView>(R.id.card_social)
        val cardWebsite = findViewById<CardView>(R.id.card_website)
        val overviewParagraphContent = findViewById<TextView>(R.id.tv_overview_paragraph_content)
        val academicOpportunitiesContent = findViewById<TextView>(R.id.tv_academic_opportunities_content)
        val languageClassesContent = findViewById<TextView>(R.id.tv_language_classes_content)
        val advancedPlacementCoursesContent = findViewById<TextView>(R.id.tv_advanced_placement_courses_content)

        backBtn.setOnClickListener {
            finish()
        }

        cardShare.addDebouncedClickListener {

        }

        cardSocial.addDebouncedClickListener {

        }

        cardWebsite.addDebouncedClickListener {

        }

    }

    companion object {
        val TAG = "SchoolDetailActivity"

        fun newIntent(context: Context) = Intent(context, SchoolDetailActivity::class.java)
    }

}