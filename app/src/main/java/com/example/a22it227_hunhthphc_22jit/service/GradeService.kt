package com.example.a22it227_hunhthphc_22jit.service

import com.example.a22it227_hunhthphc_22jit.data.Grade
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface GradeService {

    @GET("grades")
    fun getGrades(): Call<List<Grade>>

    @POST("grades/{id}")
    fun createOrUpdateGrade(@Path("id") id: Int, @Body grade: Grade): Call<Grade>

    @DELETE("grades/{id}")
    fun deleteGrade(@Path("id") id: Long): Call<Void>

    @GET("grades/{id}")
    fun getGradeById(@Path("id") id: Int): Call<Grade>
    @PUT("grades/{id}")
    fun updateGrade(@Path("id") id: Int, @Body grade: Grade): Call<Grade>

}

