package com.nicholas.ocholla.nyc.schools.mvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nicholas.ocholla.nyc.schools.mvvm.model.School
import com.nicholas.ocholla.nyc.schools.mvvm.model.SchoolsService
import com.nicholas.ocholla.nyc.schools.mvvm.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ListViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var schoolsService: SchoolsService

    @InjectMocks
    var listViewModel = ListViewModel()

    private var testSingle: Single<List<School>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getSchoolsSuccess() {
        val school = School(
            "02M260",
            "Clinton School Writers & Artists, M.S. 260",
            "M",
            "Students who are prepared for college must have an education that encourages them to take risks as they produce and perform. Our college preparatory curriculum develops writers and has built a tight-knit community. Our school develops students who can think analytically and write creatively. Our arts programming builds on our 25 years of experience in visual, performing arts and music on a middle school level. We partner with New Audience and the Whitney Museum as cultural partners. We are a International Baccalaureate (IB) candidate school that offers opportunities to take college courses at neighboring universities.",
            "1",
            "Free college courses at neighboring universities",
            "International Travel, Special Arts Programs, Music, Internships, College Mentoring English Language Learner Programs: English as a New Language",
            "English as a New Language",
            "Chelsea-Union Sq",
            "M868",
            "10 East 15th Street, Manhattan NY 10003 (40.736526, -73.992727)",
            "212-524-4360",
            "212-524-4365",
            "admissions@theclintonschool.net",
            "www.theclintonschool.net",
            "1, 2, 3, F, M to 14th St - 6th Ave; 4, 5, L, Q to 14th St-Union Square; 6, N, R to 23rd St",
            "BM1, BM2, BM3, BM4, BxM10, BxM6, BxM7, BxM8, BxM9, M1, M101, M102, M103, M14A, M14D, M15, M15-SBS, M2, M20, M23, M3, M5, M7, M8, QM21, X1, X10, X10B, X12, X14, X17, X2, X27, X28, X37, X38, X42, X5, X63, X64, X68, X7, X9",
            "6-11",
            "6-12",
            "376",
            "CUNY College Now, Technology, Model UN, Student Government, School Leadership Team, Music, School Musical, National Honor Society, The Clinton Post (School Newspaper), Clinton Soup (Literary Magazine), GLSEN, Glee",
            "Cross Country, Track and Field, Soccer, Flag Football, Basketball",
            "0.970000029",
            "0.899999976",
            "0.970000029",
            "1",
            "See theclintonschool.net for more information.",
            "Course Grades: English (87-100), Math (83-100), Social Studies (90-100), Science (88-100)",
            "Standardized Test Scores: English Language Arts (2.8-4.5), Math (2.8-4.5)",
            "Attendance and Punctuality",
            "Writing Exercise",
            "Group Interview (On-Site)",
            "Â—57% of offers went to this group",
            "M.S. 260 Clinton School Writers & Artists",
            "M64A",
            "Humanities & Interdisciplinary",
            "Screened",
            "80",
            "Y",
            "1515",
            "16",
            "Y",
            "138",
            "YesÂ–9",
            "Priority to continuing 8th graders",
            "Then to Manhattan students or residents",
            "Then to New York City residents",
            "19",
            "9",
            "10 East 15th Street",
            "Manhattan",
            "10003",
            "NY",
            "40.73653",
            "-73.9927",
            "5",
            "2",
            "52",
            "1089902",
            "1008420034",
            "Hudson Yards-Chelsea-Flatiron-Union Square",
            "MANHATTAN")

        val schoolsList = arrayListOf(school)

        testSingle = Single.just(schoolsList)

        `when`(schoolsService.getSchools()).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(1, listViewModel.schools.value?.size)
        Assert.assertEquals(false, listViewModel.schoolLoadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)
    }

    @Test
    fun getSchoolsFail() {
        testSingle = Single.error(Throwable())

        `when`(schoolsService.getSchools()).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(true, listViewModel.schoolLoadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)
    }

    @Before
    fun setUpRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }
}