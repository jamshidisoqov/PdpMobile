package io.jamshid.pdpuz.ui.main.course.list.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.jamshid.pdpuz.databinding.RcvGroupItemBinding

class CourseListAdapter : RecyclerView.Adapter<CourseListAdapter.CourseVH>() {
    private lateinit var binding: RcvGroupItemBinding

    class CourseVH(binding: RcvGroupItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseVH {
        return CourseVH(binding)
    }

    override fun onBindViewHolder(holder: CourseVH, position: Int) {

    }

    override fun getItemCount(): Int = 10
}