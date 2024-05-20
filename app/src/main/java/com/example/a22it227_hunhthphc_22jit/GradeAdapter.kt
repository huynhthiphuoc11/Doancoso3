package com.example.a22it227_hunhthphc_22jit

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.Grade

class GradeAdapter(private val grades: MutableList<Grade>) : RecyclerView.Adapter<GradeAdapter.GradeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grade_item, parent, false)
        return GradeViewHolder(view)
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        holder.bind(grades[position])
    }

    override fun getItemCount(): Int = grades.size

    inner class GradeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentIdTextView: TextView = itemView.findViewById(R.id.textViewStudentId)
        private val studentNameTextView: TextView = itemView.findViewById(R.id.textViewStudentName)
        private val regularScoreEditText: EditText = itemView.findViewById(R.id.editTextRegularScore)
        private val midtermScoreEditText: EditText = itemView.findViewById(R.id.editTextMidtermScore)
        private val finalScoreEditText: EditText = itemView.findViewById(R.id.editTextFinalScore)
        private val averageScoreEditText: EditText = itemView.findViewById(R.id.editTextAverageScore)

        fun bind(grade: Grade) {
            studentIdTextView.text = grade.studentId
            studentNameTextView.text = grade.getStudentNameFromGrade()
            regularScoreEditText.setText(grade.regularScore.toString())
            midtermScoreEditText.setText(grade.midtermScore.toString())
            finalScoreEditText.setText(grade.finalScore.toString())
            averageScoreEditText.setText(grade.averageScore.toString())

            regularScoreEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    grade.regularScore = s.toString().toFloatOrNull() ?: 0.0f
                    updateAverageScore(grade)
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            midtermScoreEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    grade.midtermScore = s.toString().toFloatOrNull() ?: 0.0f
                    updateAverageScore(grade)
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            finalScoreEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    grade.finalScore = s.toString().toFloatOrNull() ?: 0.0f
                    updateAverageScore(grade)
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }

        private fun updateAverageScore(grade: Grade) {
            grade.averageScore =(grade.regularScore + grade.midtermScore + grade.finalScore) / 3
            averageScoreEditText.setText(grade.averageScore.toString())
        }
    }
}