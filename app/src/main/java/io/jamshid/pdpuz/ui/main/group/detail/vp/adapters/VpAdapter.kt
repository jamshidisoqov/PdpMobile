package io.jamshid.pdpuz.ui.main.group.detail.vp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.databinding.GroupDetailsItemBinding

class VpAdapter:RecyclerView.Adapter<VpAdapter.ViewHolder>()  {

    private var list:List<Group> = emptyList()
    inner class ViewHolder(val binding: GroupDetailsItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun onBind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = list.size

    fun setData(list: List<Group>){
        this.list=list
    }
}