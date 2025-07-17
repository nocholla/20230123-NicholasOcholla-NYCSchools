package com.nicholas.ocholla.nyc.schools.mvvm.view.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.nicholas.ocholla.nyc.schools.mvvm.databinding.ItemSchoolBinding
import com.nicholas.ocholla.nyc.schools.mvvm.model.School
import com.nicholas.ocholla.nyc.schools.mvvm.util.addDebouncedClickListener
import com.nicholas.ocholla.nyc.schools.mvvm.view.activities.SchoolDetailActivity

class SchoolListAdapter(private val context: Context, var schools: ArrayList<School>): RecyclerView.Adapter<SchoolListAdapter.SchoolViewHolder>() {

    fun updateSchools(newSchools: List<School>) {
        schools.clear()
        schools.addAll(newSchools)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) = SchoolViewHolder(
        ItemSchoolBinding.inflate(LayoutInflater.from(parent.context), parent, false) // Use View Binding to inflate
    )

    override fun getItemCount() = schools.size

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(context, schools[position])
    }

    class SchoolViewHolder(private val binding: ItemSchoolBinding): RecyclerView.ViewHolder(binding.root) { // Pass binding here

        // Access views directly from binding object
        private val schoolCard: CardView = binding.cvSchool
        private val schoolName: TextView = binding.tvSchoolName
        private val schoolOverview: TextView = binding.tvOverviewParagraph
        private val schoolEmail: ImageButton = binding.ibSchoolEmail
        private val schoolCall: ImageButton = binding.ibSchoolCall
        private val schoolText: ImageButton = binding.ibSchoolText

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

            // Open School Detail Activity
            schoolCard.setOnClickListener {
                // Set Intent
                val intent = Intent(context, SchoolDetailActivity::class.java)

                intent.putExtra("INTENT_EXTRA_DATABASE_NAME", school.databaseName)
                intent.putExtra("INTENT_EXTRA_SCHOOL_NAME", school.schoolName)
                intent.putExtra("INTENT_EXTRA_BORO", school.boro)
                intent.putExtra("INTENT_EXTRA_OVERVIEW_PARAGRAPH", school.overviewParagraph)
                intent.putExtra("INTENT_EXTRA_SCHOOL_TENTH_SEAT", school.schoolTenthSeats)
                intent.putExtra("INTENT_EXTRA_ACADEMIC_OPPORTUNITIES_ONE", school.academicOpportunitiesOne)
                intent.putExtra("INTENT_EXTRA_ACADEMIC_OPPORTUNITIES_TWO", school.academicoOpportunitiesTwo)
                intent.putExtra("INTENT_EXTRA_ELL_PROGRAMS", school.ellPrograms)
                intent.putExtra("INTENT_EXTRA_NEIGHBORHOOD", school.neighborhood)
                intent.putExtra("INTENT_EXTRA_BUILDING_CODE", school.buildingCode)
                intent.putExtra("INTENT_EXTRA_LOCATION", school.location)
                intent.putExtra("INTENT_EXTRA_PHONE_NUMBER", school.phoneNumber)
                intent.putExtra("INTENT_EXTRA_FAX_NUMBER", school.faxNumber)
                intent.putExtra("INTENT_EXTRA_SCHOOL_EMAIL", school.schoolEmail)
                intent.putExtra("INTENT_EXTRA_WEBSITE", school.website)
                intent.putExtra("INTENT_EXTRA_BUS", school.bus)
                intent.putExtra("INTENT_EXTRA_SUBWAY", school.subway)
                intent.putExtra("INTENT_EXTRA_GRADES_2018", school.grades2018)
                intent.putExtra("INTENT_EXTRA_FINAL_GRADES", school.finalGrades)
                intent.putExtra("INTENT_EXTRA_TOTAL_STUDENTS", school.totalStudents)
                intent.putExtra("INTENT_EXTRA_EXTRA_CURRICULAR_ACTIVITIES", school.extraCurricularActivities)
                intent.putExtra("INTENT_EXTRA_SCHOOL_SPORTS", school.schoolSports)
                intent.putExtra("INTENT_EXTRA_ATTENDANCE_RATE", school.attendanceRate)
                intent.putExtra("INTENT_EXTRA_PCT_STUDENT_ENOUGH_VARIETY", school.pctStudentEnoughVariety)
                intent.putExtra("INTENT_EXTRA_PCT_STUDENT_SAFE", school.pctStudentSafe)
                intent.putExtra("INTENT_EXTRA_SCHOOL_ACCESSIBILITY_DESCRIPTION", school.schoolAccessibilityDescription)
                intent.putExtra("INTENT_EXTRA_DIRECTIONS_ONE", school.directionsOne)
                intent.putExtra("INTENT_EXTRA_REQUIREMENT_ONE", school.requirementOne)
                intent.putExtra("INTENT_EXTRA_REQUIREMENT_TWO", school.requirementTwo)
                intent.putExtra("INTENT_EXTRA_REQUIREMENT_THREE", school.requirementThree)
                intent.putExtra("INTENT_EXTRA_REQUIREMENT_FOUR", school.requirementFour)
                intent.putExtra("INTENT_EXTRA_REQUIREMENT_FIVE", school.requirementFive)
                intent.putExtra("INTENT_EXTRA_OFFER_RATE_ONE", school.offerRateOne)
                intent.putExtra("INTENT_EXTRA_PROGRAM_ONE", school.programOne)
                intent.putExtra("INTENT_EXTRA_CODE_ONE", school.codeOne)
                intent.putExtra("INTENT_EXTRA_INTEREST_ONE", school.interestOne)
                intent.putExtra("INTENT_EXTRA_METHOD_ONE", school.methodOne)
                intent.putExtra("INTENT_EXTRA_SEATS_NINE_GE_ONE", school.seatsNinegeOne)
                intent.putExtra("INTENT_EXTRA_GRADE_NINE_FILLED_FLAG_ONE", school.gradeNineFilledFlagOne)
                intent.putExtra("INTENT_EXTRA_GRADE_NINE_APPLICANT_ONE", school.gradeNineApplicantsOne)
                intent.putExtra("INTENT_EXTRA_SEATS_NINE_SWD_ONE", school.seatsNineswdOne)
                intent.putExtra("INTENT_EXTRA_GRADE_NINE_SWD_FILLED_FLAG_ONE", school.grade9swdFilledFlagOne)
                intent.putExtra("INTENT_EXTRA_GRADE_NINE_SWD_APPLICANTS_ONE", school.grade9swdApplicantsOne)
                intent.putExtra("INTENT_EXTRA_SEATS_101", school.seats101)
                intent.putExtra("INTENT_EXTRA_ADMISSIONS_PRIORITY_ELEVEN", school.admissionsPriorityEleven)
                intent.putExtra("INTENT_EXTRA_ADMISSIONS_PRIORITY_TWENTY_ONE", school.admissionsPriorityTwentyOne)
                intent.putExtra("INTENT_EXTRA_ADMISSIONS_PRIORITY_THIRTY_ONE", school.admissionsPriorityThirtyOne)
                intent.putExtra("INTENT_EXTRA_GRADE_NINE_APPLICANTS_PER_SEAT_ONE", school.gradeNineApplicantsPerSeatOne)
                intent.putExtra("INTENT_EXTRA_GRADE_NINE_SWD_APPLICANTS_PER_SEAT_ONE", school.gradeNineswdApplicantsPerSeatOne)
                intent.putExtra("INTENT_EXTRA_PRIMARY_ADDRESS_LINE_ONE", school.primaryAddressLineOne)
                intent.putExtra("INTENT_EXTRA_CITY", school.city)
                intent.putExtra("INTENT_EXTRA_ZIP", school.zip)
                intent.putExtra("INTENT_EXTRA_STATE_CODE", school.stateCode)
                intent.putExtra("INTENT_EXTRA_LATITUDE", school.latitude)
                intent.putExtra("INTENT_EXTRA_LONGITUDE", school.longitude)
                intent.putExtra("INTENT_EXTRA_COMMUNITY_BOARD", school.communityBoard)
                intent.putExtra("INTENT_EXTRA_COUNCIL_DISTRICT", school.councilDistrict)
                intent.putExtra("INTENT_EXTRA_CENSUS_TRACT", school.censusTract)
                intent.putExtra("INTENT_EXTRA_BIN", school.bin)
                intent.putExtra("INTENT_EXTRA_BBL", school.bbl)
                intent.putExtra("INTENT_EXTRA_NTA", school.nta)
                intent.putExtra("INTENT_EXTRA_BOROUGH", school.borough)

                context.startActivity(intent)
            }
        }
    }
}