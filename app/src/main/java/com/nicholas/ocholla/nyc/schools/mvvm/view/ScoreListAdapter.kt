package com.nicholas.ocholla.nyc.schools.mvvm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholas.ocholla.nyc.schools.mvvm.R
import com.nicholas.ocholla.nyc.schools.mvvm.model.Score
import kotlinx.android.synthetic.main.item_score.view.*

class ScoreListAdapter(private val context: Context, var scores: ArrayList<Score>): RecyclerView.Adapter<ScoreListAdapter.ScoreViewHolder>() {

    fun updateScores(newScores: List<Score>) {
        scores.clear()
        scores.addAll(newScores)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) = ScoreViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false)
    )

    override fun getItemCount() = scores.size

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.bind(context, scores[position])
    }

    class ScoreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val scoreName: TextView = itemView.tv_school_name
        private val scoreMath: TextView = itemView.tv_sat_scores_math
        private val scoreReading: TextView = itemView.tv_sat_scores_reading
        private val scoreWriting: TextView = itemView.tv_sat_scores_writing

        fun bind(context: Context, score: Score) {
            val name = score.schoolName
            val math = score.satMathAvgScore
            val reading = score.satCriticalReadingAverageScore
            val writing = score.satWritingAverageScore

            scoreName.text = name
            scoreMath.text = math
            scoreReading.text = reading
            scoreWriting.text = writing
        }

    }

}