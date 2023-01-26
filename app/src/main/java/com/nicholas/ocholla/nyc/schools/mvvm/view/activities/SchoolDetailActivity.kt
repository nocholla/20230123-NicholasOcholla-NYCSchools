package com.nicholas.ocholla.nyc.schools.mvvm.view.activities

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
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

        // Hide Toolbar
        supportActionBar?.hide()

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
        val website = intent.getStringExtra("INTENT_EXTRA_WEBSITE")

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
            val targetShareIntents = ArrayList<Intent>()
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"

            val pm = this.packageManager
            val resInfos = pm?.queryIntentActivities(shareIntent, 0)

            if (resInfos != null) {
                if (resInfos.isNotEmpty()) {
                    for (resInfo in resInfos) {

                        val packageName = resInfo.activityInfo.packageName

                        if (packageName.contains("com.google.android.gm") || packageName.contains("com.yahoo.mobile")) {

                            val intent = Intent()
                            intent.component = ComponentName(packageName, resInfo.activityInfo.name)
                            intent.putExtra("AppName", resInfo.loadLabel(pm).toString())
                            intent.action = Intent.ACTION_SEND
                            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                            intent.type = "text/plain"
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Parent Feedback!")
                            intent.putExtra(Intent.EXTRA_TEXT, "Hello, NYC City Schools")
                            intent.setPackage(packageName)
                            targetShareIntents.add(intent)

                        }
                    }

                    if (targetShareIntents.isNotEmpty()) {
                        targetShareIntents.sortWith(Comparator { o1, o2 ->
                            o2.getStringExtra("AppName")?.let {
                                o1.getStringExtra("AppName")
                                    ?.compareTo(it)
                            }!!
                        })
                        val chooserIntent = Intent.createChooser(targetShareIntents.removeAt(0), "Send Feedback Via")
                        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toTypedArray<Parcelable>())
                        this.startActivity(chooserIntent)
                    } else {
                        Toast.makeText(this, getString(R.string.toast_no_app_to_share), Toast.LENGTH_LONG).show()
                    }

                }
            }
        }

        socialCV.addDebouncedClickListener {
            shareSocial()
        }

        websiteCV.addDebouncedClickListener {
            // Open School Website
            val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$website"))
            startActivity(urlIntent)
        }

    }

    private fun shareSocial() {

        val targetShareIntents = ArrayList<Intent>()
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"

        val pm = this.packageManager
        val resInfos = pm?.queryIntentActivities(shareIntent, 0)
        if (!resInfos?.isEmpty()!!) {
            for (resInfo in resInfos) {

                val packageName = resInfo.activityInfo.packageName

                if (packageName.contains("com.twitter.android")
                    || packageName.contains("com.facebook.katana")
                    || packageName.contains("com.whatsapp")
                    || packageName.contains("com.google.android.apps.plus")
                    || packageName.contains("com.google.android.talk")
                    || packageName.contains("com.slack")
                    || packageName.contains("com.google.android.gm")
                    || packageName.contains("com.facebook.orca")
                    || packageName.contains("com.yahoo.mobile")
                    || packageName.contains("com.skype.raider")
                    || packageName.contains("com.android.mms")
                    || packageName.contains("com.linkedin.android")
                    || packageName.contains("com.google.android.apps.messaging")) {

                    val intent = Intent()
                    intent.component = ComponentName(packageName, resInfo.activityInfo.name)
                    intent.putExtra("AppName", resInfo.loadLabel(pm).toString())
                    intent.action = Intent.ACTION_SEND
                    //intent.setType("image/*");
                    intent.type = "text/plain"
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Check out the NYC Schools")
                    intent.putExtra(
                        Intent.EXTRA_TEXT, "NYC Schools"
                                + " \n "
                                + " \n "
                                + "Get more info about NYC Schools"
                                + " \n "
                                + " \n "
                                + "Thank you!")

                    intent.setPackage(packageName)
                    targetShareIntents.add(intent)

                }
            }
            if (!targetShareIntents.isEmpty()) {
                targetShareIntents.sortWith(Comparator { o1, o2 ->
                    o2.getStringExtra("AppName")?.let {
                        o1.getStringExtra("AppName")
                            ?.compareTo(it)
                    }!!
                })
                val chooserIntent = Intent.createChooser(targetShareIntents.removeAt(0), "Share Via")
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toTypedArray<Parcelable>())
                this.startActivity(chooserIntent)
            } else {
                Toast.makeText(this, getString(R.string.toast_no_app_to_share), Toast.LENGTH_LONG).show()
            }

        }

    }

    companion object {
        val TAG = "SchoolDetailActivity"

        fun newIntent(context: Context) = Intent(context, SchoolDetailActivity::class.java)
    }

}