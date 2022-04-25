package io.jamshid.pdpuz.ui.main.mentor.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.course.Course
import io.jamshid.pdpuz.databinding.RcvGroupItemBinding
import io.jamshid.pdpuz.domain.interfases.OnItemClickListener

class MentorsAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MentorsAdapter.ViewHolder>() {

    private var courseList: List<Course> = emptyList()
    private lateinit var binding: RcvGroupItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RcvGroupItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.rcv_group_item, parent, false)
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(courseList[position])
    }

    override fun getItemCount(): Int = courseList.size


    inner class ViewHolder(val binding: RcvGroupItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(course: Course) {
            binding.tvCourseName.text = course.courseName
            binding.root.setOnClickListener {
                onItemClickListener.onClick(course)
            }
        }
    }

    fun setData(list: List<Course>) {
        courseList = list
        notifyDataSetChanged()
    }
}
