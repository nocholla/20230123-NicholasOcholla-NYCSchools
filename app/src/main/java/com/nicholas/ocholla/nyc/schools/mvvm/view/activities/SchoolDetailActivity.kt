package com.nicholas.ocholla.nyc.schools.mvvm.view.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
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
    @SuppressLint("SourceLockedOrientationActivity", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_detail)

        // Orientation Check
        if (!isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        // Get Intents from School List Adapter
        // Intents
        val intent = intent

        val schoolName = intent.getStringExtra("INTENT_EXTRA_SCHOOL_NAME")
        val overviewParagraph = intent.getStringExtra("INTENT_EXTRA_OVERVIEW_PARAGRAPH")
        val location = intent.getStringExtra("INTENT_EXTRA_LOCATION")
        val phone = intent.getStringExtra("INTENT_EXTRA_PHONE_NUMBER")
        val email = intent.getStringExtra("INTENT_EXTRA_SCHOOL_NAME")

        // Widgets
        val backBtn = findViewById<ImageButton>(R.id.ib_close)
        val schoolNameTV = findViewById<TextView>(R.id.tv_school_name_content)
        val shareCV = findViewById<CardView>(R.id.card_share)
        val socialCV = findViewById<CardView>(R.id.card_social)
        val websiteCV = findViewById<CardView>(R.id.card_website)
        val overviewParagraphContentTV = findViewById<TextView>(R.id.tv_overview_paragraph_content)
        val locationTV = findViewById<TextView>(R.id.tv_location_content)
        val phoneTV = findViewById<TextView>(R.id.tv_phone_content)
        val emailTV = findViewById<TextView>(R.id.tv_email_content)

        // Bind Data
        schoolNameTV.text = schoolName
        overviewParagraphContentTV.text = overviewParagraph
        locationTV.text = location
        phoneTV.text = phone
        emailTV.text = email

        backBtn.setOnClickListener {
            finish()
        }

        shareCV.addDebouncedClickListener {

        }

        socialCV.addDebouncedClickListener {

        }

        websiteCV.addDebouncedClickListener {

        }

    }

    companion object {
        val TAG = "SchoolDetailActivity"

        fun newIntent(context: Context) = Intent(context, SchoolDetailActivity::class.java)
    }

}