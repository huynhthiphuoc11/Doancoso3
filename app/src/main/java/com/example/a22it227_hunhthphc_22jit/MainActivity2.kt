package com.example.a22it227_hunhthphc_22jit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.a22it227_hunhthphc_22jit.data.Notification
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import com.example.a22it227_hunhthphc_22jit.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_home)
        val cardHomeImage: CardView = findViewById(R.id.card_home_image)
        val cardHomeImage1: CardView = findViewById(R.id.card_home_image1)


        val images = arrayOf(
            R.drawable.slider11,
            R.drawable.slider22,
            R.drawable.slider33
        )

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val adapter = ImageSliderAdapter(images)
        viewPager.adapter = adapter
        cardHomeImage.setOnClickListener {
            // Tạo Intent để mở ClassActivity
            val intent = Intent(this, ClassActivity::class.java)
            startActivity(intent)
        }
        cardHomeImage1.setOnClickListener {
            // Tạo Intent để mở ClassActivity
            val intent = Intent(this, ClassActivity::class.java)
            startActivity(intent)
        }

    }


}
