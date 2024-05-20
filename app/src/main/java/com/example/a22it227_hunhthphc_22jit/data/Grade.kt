package com.example.a22it227_hunhthphc_22jit.data

data class Grade(
    val id: Int,
    val studentId: String,
    val studentName: String,
    var regularScore: Float,
    var midtermScore: Float,
    var finalScore: Float,
    var averageScore: Float
) {
    fun getStudentNameFromGrade(): String {
        return studentName
    }
}
