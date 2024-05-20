package com.example.a22it227_hunhthphc_22jit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.Classes

class ClassesAdapter(private val classesList: List<Classes>, private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ClassesAdapter.ClassViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.class_item, parent, false)
        return ClassViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val currentClass = classesList[position]
        holder.bind(currentClass)
        holder.itemView.setOnClickListener {
            onItemClick(currentClass.id.toInt())
        }
    }

    override fun getItemCount(): Int {
        return classesList.size
    }

    class ClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtClassName: TextView = itemView.findViewById(R.id.tvClassName)
        private val txtClassCode: TextView = itemView.findViewById(R.id.textClassId)
        private val txtTotal: TextView = itemView.findViewById(R.id.tvTotal)

        fun bind(currentClass: Classes) {
            txtClassName.text = currentClass.className
            txtTotal.text = currentClass.total.toString()
            txtClassCode.text = currentClass.id.toString()
        }
    }
}
