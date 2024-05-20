package com.example.a22it227_hunhthphc_22jit.service

import com.example.a22it227_hunhthphc_22jit.data.LoginRequest
import com.example.a22it227_hunhthphc_22jit.data.Notification
import com.example.a22it227_hunhthphc_22jit.data.SignUpRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST("/api/user")
    fun signup(@Body request: SignUpRequest): Call<SignUpRequest>

    @POST("/api/user")
    fun login(@Body request: LoginRequest): Call<LoginRequest>

    @GET("notifications")
    fun getNotifications(): List<Notification> // Define Notification class




}
