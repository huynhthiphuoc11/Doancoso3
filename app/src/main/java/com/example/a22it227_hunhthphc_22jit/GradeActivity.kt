package com.example.a22it227_hunhthphc_22jit

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.Grade
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import com.example.a22it227_hunhthphc_22jit.service.GradeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GradeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GradeAdapter
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade)

        recyclerView = findViewById(R.id.recyclerViewGrades)
        buttonSave = findViewById(R.id.buttonSave)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val grades = mutableListOf<Grade>()
        adapter = GradeAdapter(grades)
        recyclerView.adapter = adapter

        val service = RetrofitClient.getRetrofitInstance().create(GradeService::class.java)
        val call = service.getGrades()
        call.enqueue(object : Callback<List<Grade>> {
            override fun onResponse(call: Call<List<Grade>>, response: Response<List<Grade>>) {
                if (response.isSuccessful) {
                    val retrievedGrades = response.body() ?: emptyList()
                    grades.clear()
                    grades.addAll(retrievedGrades)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@GradeActivity, "Failed to load grades", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Grade>>, t: Throwable) {
                Toast.makeText(this@GradeActivity, "Failed to load grades: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        buttonSave.setOnClickListener {
            val dataservice = RetrofitClient.getRetrofitInstance().create(GradeService::class.java)
            val grade = grades.firstOrNull() ?: return@setOnClickListener
            val submitCall = service.createOrUpdateGrade(grade.id, grade)
            submitCall.enqueue(object : Callback<Grade> {
                override fun onResponse(call: Call<Grade>, response: Response<Grade>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@GradeActivity, "Grades submitted successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@GradeActivity, "Failed to submit grades", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Grade>, t: Throwable) {
                    Toast.makeText(this@GradeActivity, "Failed to submit grades: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
