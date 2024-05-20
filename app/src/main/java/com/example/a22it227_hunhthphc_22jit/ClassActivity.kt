package com.example.a22it227_hunhthphc_22jit

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.Classes
import com.example.a22it227_hunhthphc_22jit.data.Grade
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var classesAdapter: ClassesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class)
        val btnBack: ImageButton = findViewById(R.id.btnBack)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        btnBack.setOnClickListener {
            // Quay lại MainActivity2
            finish()
        }
        fetchClasses()
    }

    private fun fetchClasses() {
        RetrofitClient.instance.getAllClasses().enqueue(object : Callback<List<Classes>> {
            override fun onResponse(call: Call<List<Classes>>, response: Response<List<Classes>>) {
                if (response.isSuccessful) {
                    val classesList = response.body() ?: emptyList()
                    classesAdapter = ClassesAdapter(classesList) { classId ->
                        // Xử lý sự kiện click vào một lớp
                        openAttendanceActivity(classId)

                    }
                    recyclerView.adapter = classesAdapter
                }
            }

            override fun onFailure(call: Call<List<Classes>>, t: Throwable) {
                // Xử lý lỗi
            }
        })
    }

    private fun openAttendanceActivity(classId: Int) {
        val intent = Intent(this, AttendanceActivity::class.java)
        intent.putExtra("class_id", classId)
        startActivity(intent)
    }

}
