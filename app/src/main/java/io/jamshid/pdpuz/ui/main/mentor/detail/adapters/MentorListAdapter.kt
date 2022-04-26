package io.jamshid.pdpuz.ui.main.mentor.detail.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.mentor.Mentor
import io.jamshid.pdpuz.databinding.RcvMentorItemBinding
import io.jamshid.pdpuz.domain.interfases.OnCustomItemClickListener

class MentorListAdapter(private val onCustomItemClickListener: OnCustomItemClickListener<Mentor>) : RecyclerView.Adapter<MentorListAdapter.ViewHolder>() {

    private var mentorList: List<Mentor> = emptyList()
    private lateinit var binding: RcvMentorItemBinding

    inner class ViewHolder(val binding: RcvMentorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(mentor: Mentor) {
            binding.apply {
                tvMentorName.text = "${mentor.firstName} ${mentor.lastName}"
                tvMentorFathername.text = mentor.middleName
                binding.imgMentorEdit.setOnClickListener {
                    onCustomItemClickListener.onEditItemClick(mentor)
                }
                binding.imgMentorTrash.setOnClickListener {
                    onCustomItemClickListener.onTrashItemClick(mentor)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RcvMentorItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.rcv_mentor_item, parent, false)
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(mentorList[position])
    }

    override fun getItemCount(): Int = mentorList.size

    fun setData(list: List<Mentor>) {
        mentorList = list
        notifyDataSetChanged()
    }

}