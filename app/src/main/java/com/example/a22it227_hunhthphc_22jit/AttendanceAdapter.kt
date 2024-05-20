package com.example.a22it227_hunhthphc_22jit.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.R
import java.text.SimpleDateFormat
import java.util.*

class AttendanceAdapter(
    private val context: Context,
    private var attendanceList: List<Attendance>
) : RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.attendance_item, parent, false)
        return AttendanceViewHolder(view)
    }

    fun setData(attendanceList: List<Attendance>) {
        this.attendanceList = attendanceList
        notifyDataSetChanged()
    }

    fun getData(): List<Attendance> {
        return attendanceList
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        val attendance = attendanceList[position]
        holder.bind(attendance)
    }

    override fun getItemCount(): Int {
        return attendanceList.size
    }

    inner class AttendanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentIdTextView: TextView = itemView.findViewById(R.id.textStudentId)
        private val studentNameTextView: TextView = itemView.findViewById(R.id.textStudentName)
        private val attendanceDateTextView: TextView = itemView.findViewById(R.id.textStudentDate)
        private val statusSpinner: Spinner = itemView.findViewById(R.id.spinnerStudentStatus)

        fun bind(attendance: Attendance) {
            studentIdTextView.text = attendance.studentId.toString()
            studentNameTextView.text = attendance.student_name
            attendanceDateTextView.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(attendance.attendanceDate)

            // Set up the spinner
            val statusAdapter = ArrayAdapter.createFromResource(
                context,
                R.array.student_status_choices,
                android.R.layout.simple_spinner_item
            )
            statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            statusSpinner.adapter = statusAdapter
            statusSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    val newStatus = when (position) {
                        0 -> AttendanceStatus.PRESENT
                        1 -> AttendanceStatus.ABSENT
                        2 -> AttendanceStatus.LATE
                        else -> AttendanceStatus.PRESENT
                    }
                    attendance.status = newStatus
                    // Cập nhật dữ liệu trong cơ sở dữ liệu bằng cách gọi phương thức updateAttendance trong DAO
                    // attendanceDao.updateAttendance(attendance)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Không làm gì
                }
            }
            // Set spinner selection based on attendance status
            attendance.status?.let { status ->
                val statusIndex = when (status) {
                    AttendanceStatus.PRESENT -> 0
                    AttendanceStatus.ABSENT -> 1
                    AttendanceStatus.LATE -> 2
                }
                statusSpinner.setSelection(statusIndex)
            }
        }

    }
}
