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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.nicholas.ocholla.nyc.schools.mvvm.R
import com.nicholas.ocholla.nyc.schools.mvvm.util.addDebouncedClickListener
import com.nicholas.ocholla.nyc.schools.mvvm.databinding.ActivitySchoolDetailBinding

class SchoolDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySchoolDetailBinding // Declare binding

    // Orientation
    private val isTablet: Boolean
        get() = (
                this.resources.configuration.screenLayout and
                        Configuration.SCREENLAYOUT_SIZE_MASK >=
                        Configuration.SCREENLAYOUT_SIZE_LARGE)

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SourceLockedOrientationActivity", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchoolDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide Toolbar
        supportActionBar?.hide()

        // Orientation Check
        if (!isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        // Get Intents from School List Adapter
        val intent = intent

        val schoolName = intent.getStringExtra("INTENT_EXTRA_SCHOOL_NAME")
        val overviewParagraph = intent.getStringExtra("INTENT_EXTRA_OVERVIEW_PARAGRAPH")
        val location = intent.getStringExtra("INTENT_EXTRA_LOCATION")
        val phone = intent.getStringExtra("INTENT_EXTRA_PHONE_NUMBER")
        val email = intent.getStringExtra("INTENT_EXTRA_SCHOOL_EMAIL")
        val website = intent.getStringExtra("INTENT_EXTRA_WEBSITE")
        val dbn = intent.getStringExtra("INTENT_EXTRA_DATABASE_NAME")

        // Bind Data using binding object
        binding.tvSchoolNameContent.text = schoolName
        binding.tvOverviewParagraphContent.text = overviewParagraph
        binding.tvLocationContent.text = location
        binding.tvPhoneContent.text = phone
        binding.tvEmailContent.text = email

        binding.ibClose.setOnClickListener {
            finish()
        }

        // School Feedback
        binding.cardShare.addDebouncedClickListener { // Access via binding
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
                        if (packageName.contains("com.google.android.gm") ||
                            packageName.contains("com.yahoo.mobile")) {
                            val intent = Intent()
                            intent.component = ComponentName(
                                packageName,
                                resInfo.activityInfo.name)
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
                                o1.getStringExtra("AppName")?.compareTo(it)
                            }!!
                        })
                        val chooserIntent = Intent.createChooser(
                            targetShareIntents.removeAt(0), "Send Feedback Via")
                        chooserIntent.putExtra(
                            Intent.EXTRA_INITIAL_INTENTS,
                            targetShareIntents.toTypedArray<Parcelable>())
                        this.startActivity(chooserIntent)
                    } else {
                        Toast.makeText(
                            this,
                            getString(R.string.toast_no_app_to_share),
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        // Share School On Social Media
        binding.cardSocial.addDebouncedClickListener {
            shareSocial()
        }

        // Open School Website
        binding.cardWebsite.addDebouncedClickListener {
            website?.let {
                val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$it"))
                startActivity(urlIntent)
            }
        }

        // Open School Scores
        binding.fabSchoolScores.addDebouncedClickListener {
            val scoresIntent = Intent(this, ScoresActivity::class.java).apply {
                putExtra("INTENT_EXTRA_DATABASE_NAME", dbn)
            }
            startActivity(scoresIntent)
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
                        o1.getStringExtra("AppName")?.compareTo(it)
                    }!!
                })
                val chooserIntent = Intent.createChooser(
                    targetShareIntents.removeAt(0), "Share Via"
                )
                chooserIntent.putExtra(
                    Intent.EXTRA_INITIAL_INTENTS,
                    targetShareIntents.toTypedArray<Parcelable>()
                )
                this.startActivity(chooserIntent)
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.toast_no_app_to_share),
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        val TAG = "SchoolDetailActivity"
        fun newIntent(context: Context) = Intent(context, SchoolDetailActivity::class.java)
    }
}