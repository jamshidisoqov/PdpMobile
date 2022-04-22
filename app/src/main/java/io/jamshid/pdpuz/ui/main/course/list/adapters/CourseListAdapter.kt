package io.jamshid.pdpuz.ui.main.course.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.course.Course
import io.jamshid.pdpuz.databinding.RcvGroupItemBinding
import io.jamshid.pdpuz.domain.interfases.OnItemClickListener

class CourseListAdapter(var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<CourseListAdapter.CourseListVH>() {
    private lateinit var binding: RcvGroupItemBinding
    private var courseList: List<Course> = emptyList()

    class CourseListVH(private var binding: RcvGroupItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(course: Course) {
            binding.apply {
                tvCourseName.text = course.courseName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseListVH {
        binding = RcvGroupItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.rcv_group_item, parent, false)
        )
        return CourseListVH(binding)
    }

    override fun onBindViewHolder(holder: CourseListVH, position: Int) {
        holder.onBind(courseList!![position])
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(courseList[position])
        }
    }

    override fun getItemCount(): Int = courseList.size

    fun setData(list:List<Course>){
        this.courseList=list
        notifyDataSetChanged()
    }
}