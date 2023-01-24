package com.nicholas.ocholla.nyc.schools.mvvm.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * School data class.
 */
data class School(
    @SerializedName("dbn")
    var databaseName: String,
    @SerializedName("school_name")
    var schoolName: String,
    @SerializedName("boro")
    var boro: String,
    @SerializedName("overview_paragraph")
    var overviewParagraph: String,
    @SerializedName("school_10th_seats")
    var schoolTenthSeats: String,
    @SerializedName("academicopportunities1")
    var academicOpportunitiesOne: String,
    @SerializedName("academicopportunities2")
    var academicoOpportunitiesTwo: String,
    @SerializedName("ell_programs")
    var ellPrograms: String,
    @SerializedName("neighborhood")
    var neighborhood: String,
    @SerializedName("building_code")
    var buildingCode: String,
    @SerializedName("location")
    var location: String,
    @SerializedName("phone_number")
    var phoneNumber: String,
    @SerializedName("fax_number")
    var faxNumber: String,
    @SerializedName("school_email")
    var schoolEmail: String,
    @SerializedName("website")
    var website: String,
    @SerializedName("bus")
    var bus: String,
    @SerializedName("subway")
    var subway: String,
    @SerializedName("grades2018")
    var grades2018: String,
    @SerializedName("finalgrades")
    var finalGrades: String,
    @SerializedName("total_students")
    var totalStudents: String,
    @SerializedName("extracurricular_activities")
    var extraCurricularActivities: String,
    @SerializedName("school_sports")
    var schoolSports: String,
    @SerializedName("attendance_rate")
    var attendanceRate: String,
    @SerializedName("pct_stu_enough_variety")
    var pctStudentEnoughVariety: String,
    @SerializedName("pct_stu_safe")
    var pctStudentSafe: String,
    @SerializedName("school_accessibility_description")
    var schoolAccessibilityDescription: String,
    @SerializedName("directions1")
    var directionsOne: String,
    @SerializedName("requirement1_1")
    var requirementOne: String,
    @SerializedName("requirement2_1")
    var requirementTwo: String,
    @SerializedName("requirement3_1")
    var requirementThree: String,
    @SerializedName("requirement4_1")
    var requirementFour: String,
    @SerializedName("requirement5_1")
    var requirementFive: String,
    @SerializedName("offer_rate1")
    var offerRateOne: String,
    @SerializedName("program1")
    var programOne: String,
    @SerializedName("code1")
    var codeOne: String,
    @SerializedName("interest1")
    var interestOne: String,
    @SerializedName("method1")
    var methodOne: String,
    @SerializedName("seats9ge1")
    var seatsNinegeOne: String,
    @SerializedName("grade9gefilledflag1")
    var gradeNineFilledFlagOne: String,
    @SerializedName("grade9geapplicants1")
    var gradeNineApplicantsOne: String,
    @SerializedName("seats9swd1")
    var seatsNineswdOne: String,
    @SerializedName("grade9swdfilledflag1")
    var grade9swdFilledFlagOne: String,
    @SerializedName("grade9swdapplicants1")
    var grade9swdApplicantsOne: String,
    @SerializedName("seats101")
    var seats101: String,
    @SerializedName("admissionspriority11")
    var admissionsPriorityEleven: String,
    @SerializedName("admissionspriority21")
    var admissionsPriorityTwentyOne: String,
    @SerializedName("admissionspriority31")
    var admissionsPriorityThirtyOne: String,
    @SerializedName("grade9geapplicantsperseat1")
    var gradeNineApplicantsPerSeatOne: String,
    @SerializedName("grade9swdapplicantsperseat1")
    var gradeNineswdApplicantsPerSeatOne: String,
    @SerializedName("primary_address_line_1")
    var primaryAddressLineOne: String,
    @SerializedName("city")
    var city: String,
    @SerializedName("zip")
    var zip: String,
    @SerializedName("state_code")
    var stateCode: String,
    @SerializedName("latitude")
    var latitude: String,
    @SerializedName("longitude")
    var longitude: String,
    @SerializedName("community_board")
    var communityBoard: String,
    @SerializedName("council_district")
    var councilDistrict: String,
    @SerializedName("census_tract")
    var censusTract: String,
    @SerializedName("bin")
    var bin: String,
    @SerializedName("bbl")
    var bbl: String,
    @SerializedName("nta")
    var nta: String,
    @SerializedName("borough")
    var borough: String
) : Serializable
