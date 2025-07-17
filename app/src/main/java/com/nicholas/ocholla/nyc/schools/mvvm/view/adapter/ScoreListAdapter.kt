package com.nicholas.ocholla.nyc.schools.mvvm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholas.ocholla.nyc.schools.mvvm.databinding.ItemScoreBinding
import com.nicholas.ocholla.nyc.schools.mvvm.model.Score

class ScoreListAdapter(
    private val context: Context,
    var scores: ArrayList<Score>
): RecyclerView.Adapter<ScoreListAdapter.ScoreViewHolder>() {

    fun updateScores(newScores: List<Score>) {
        scores.clear()
        scores.addAll(newScores)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) = ScoreViewHolder(
        ItemScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = scores.size

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.bind(context, scores[position])
    }

    class ScoreViewHolder(
        private val binding: ItemScoreBinding
    ): RecyclerView.ViewHolder(binding.root) {

        // Access views directly from binding object
        private val scoreName: TextView = binding.tvSchoolName
        private val scoreMath: TextView = binding.tvSatScoresMath
        private val scoreReading: TextView = binding.tvSatScoresReading
        private val scoreWriting: TextView = binding.tvSatScoresWriting

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