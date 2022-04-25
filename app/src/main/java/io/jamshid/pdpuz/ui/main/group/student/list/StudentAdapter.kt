package io.jamshid.pdpuz.ui.main.group.student.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.student.Student
import io.jamshid.pdpuz.databinding.RcvStudentListItemBinding

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    private lateinit var binding: RcvStudentListItemBinding
    private var studentList: List<Student> = emptyList()

    inner class ViewHolder(val binding: RcvStudentListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(student: Student) {
            binding.apply {
                tvStudentNameOfGroup.text = "${student.firstName} ${student.lastName}"
                tvFatherNameOfGroup.text = student.middleName
                tvDateOfGroup.text=student.birthDate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RcvStudentListItemBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rcv_student_list_item, parent, false)
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(studentList[position])
    }

    override fun getItemCount(): Int = studentList.size

    fun setData(list: List<Student>) {
        studentList = list
    }

}