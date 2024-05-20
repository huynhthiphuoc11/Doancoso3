package com.example.a22it227_hunhthphc_22jit

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import com.example.a22it227_hunhthphc_22jit.data.Attendance
import com.example.a22it227_hunhthphc_22jit.data.AttendanceAdapter
import com.example.a22it227_hunhthphc_22jit.service.AttendanceService
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class AttendanceActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var attendanceAdapter: AttendanceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attendance_activity)
        val btnBack: ImageButton = findViewById(R.id.btnBack)
        recyclerView = findViewById(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)
        attendanceAdapter = AttendanceAdapter(this, mutableListOf())
        recyclerView.adapter = attendanceAdapter
        btnBack.setOnClickListener {
            // Quay lại MainActivity2
            finish()
        }

        getAttendance()

        val btnSave: Button = findViewById(R.id.btnSave)
        btnSave.setOnClickListener {
            saveAttendanceData()
        }
    }

    private fun getAttendance() {
        val service = RetrofitClient.getRetrofitInstance().create(AttendanceService::class.java)
        val call = service.getAttendance()

        call.enqueue(object : Callback<List<Attendance>> {
            override fun onResponse(call: Call<List<Attendance>>, response: Response<List<Attendance>>) {
                if (response.isSuccessful) {
                    response.body()?.let { attendanceList ->
                        attendanceAdapter.setData(attendanceList)

                    }
                } else {
                    // Handle error response
                    Log.e("AttendanceActivity", "Failed to retrieve attendance: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Attendance>>, t: Throwable) {
                // Handle network errors
                Log.e("AttendanceActivity", "Error retrieving attendance", t)
            }
        })
    }

    private fun saveAttendanceData() {
        val attendanceData = attendanceAdapter.getData()
        val service = RetrofitClient.getRetrofitInstance().create(AttendanceService::class.java)
        val call = service.saveAttendance(attendanceData)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("AttendanceActivity", "Attendance data saved successfully")
                } else {
                    Log.e("AttendanceActivity", "Failed to save attendance data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("AttendanceActivity", "Error saving attendance data", t)
            }
        })
    }
}
