package io.jamshid.pdpuz.ui.main.group.detail.vp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.databinding.GroupDetailsItemBinding
import io.jamshid.pdpuz.domain.interfases.OnCustomItemClickListener
import io.jamshid.pdpuz.domain.interfases.OnItemClickListener

class DetailsAdapter(private val onCustomItemClickListener: OnCustomItemClickListener<Group>) : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {
    private lateinit var binding: GroupDetailsItemBinding
    private var list: List<Group> = emptyList()

    inner class ViewHolder(private val binding: GroupDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(group: Group) {
            binding.tvGroupName.text=group.groupName
            binding.root.setOnClickListener {
                onCustomItemClickListener.onItemClick(group)
            }
            binding.imgGroupEdit.setOnClickListener {
                onCustomItemClickListener.onEditItemClick(group)
            }
            binding.imgGroupTrash.setOnClickListener {
                onCustomItemClickListener.onTrashItemClick(group)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = GroupDetailsItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.group_details_item, parent, false)
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setData(list: List<Group>) {
        this.list = list
        notifyDataSetChanged()
    }
}