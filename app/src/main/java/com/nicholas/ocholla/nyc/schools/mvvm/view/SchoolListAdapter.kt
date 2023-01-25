package com.nicholas.ocholla.nyc.schools.mvvm.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.nicholas.ocholla.nyc.schools.mvvm.R
import com.nicholas.ocholla.nyc.schools.mvvm.model.School
import com.nicholas.ocholla.nyc.schools.mvvm.util.addDebouncedClickListener
import kotlinx.android.synthetic.main.item_school.view.*

class SchoolListAdapter(private val context: Context, var schools: ArrayList<School>): RecyclerView.Adapter<SchoolListAdapter.SchoolViewHolder>() {

    fun updateSchools(newSchools: List<School>) {
        schools.clear()
        schools.addAll(newSchools)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) = SchoolViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_school, parent, false)
    )

    override fun getItemCount() = schools.size

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(context, schools[position])
    }

    class SchoolViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val schoolCard: CardView = itemView.cv_school
        private val schoolName: TextView = itemView.tv_school_name
        private val schoolOverview: TextView = itemView.tv_overview_paragraph
        private val schoolEmail: ImageButton = itemView.ib_school_email
        private val schoolCall: ImageButton = itemView.ib_school_call
        private val schoolText: ImageButton = itemView.ib_school_text

        fun bind(context: Context, school: School) {
            val name = school.schoolName
            val overview = school.overviewParagraph
            val email = school.schoolEmail
            val phone = school.phoneNumber

            schoolName.text = name

            schoolOverview.text = overview

            // Send email to School
            schoolEmail.addDebouncedClickListener {
                val emailIntent = Intent(Intent.ACTION_SENDTO)
                emailIntent.data = Uri.parse("mailto: $email")
                emailIntent.putExtra(Intent.EXTRA_EMAIL, "New York Schools")
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello, Parent ...")

                context.startActivity(emailIntent)
            }

            // Make phone call to School
            schoolCall.addDebouncedClickListener {
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:$phone")

                context.startActivity(dialIntent)
            }

            // Send SMS to School
            schoolText.addDebouncedClickListener {
                val uri = Uri.parse("smsto:$phone")
                val intent = Intent(Intent.ACTION_SENDTO, uri)
                intent.putExtra("sms_body", "Hello, Parent ...")

                context.startActivity(intent)
            }

            // Open School detail activity
            schoolCard.setOnClickListener {

            }
        }

    }

}