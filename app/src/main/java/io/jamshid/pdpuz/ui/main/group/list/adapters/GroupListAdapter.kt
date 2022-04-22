package io.jamshid.pdpuz.ui.main.group.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.course.Course
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.databinding.RcvGroupItemBinding
import io.jamshid.pdpuz.domain.interfases.OnItemClickListener

class GroupListAdapter(private var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<GroupListAdapter.CourseVH>() {
    private lateinit var binding: RcvGroupItemBinding
    private var courseList: List<Course> = emptyList()

    class CourseVH(val binding: RcvGroupItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(course: Course) {
            binding.tvCourseName.text = course.courseName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseVH {
        binding = RcvGroupItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.rcv_group_item, parent, false)
        )
        return CourseVH(binding)
    }

    override fun onBindViewHolder(holder: CourseVH, position: Int) {
        holder.onBind(courseList[position])
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(courseList[position])
        }
    }

    override fun getItemCount(): Int = courseList.size

    fun setData(list: List<Course>) {
        this.courseList = list
        notifyDataSetChanged()
    }

}