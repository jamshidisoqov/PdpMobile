package io.jamshid.pdpuz.ui.main.group.detail.vp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.databinding.FragmentGroupsBinding
import io.jamshid.pdpuz.domain.interfases.OnItemClickListener
import io.jamshid.pdpuz.ui.main.group.detail.GroupDetailFragmentDirections
import io.jamshid.pdpuz.ui.main.group.detail.vp.adapters.DetailsAdapter
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class GroupsFragment : Fragment() {

    private var _binding:FragmentGroupsBinding? = null
    private val binding:FragmentGroupsBinding get() = _binding!!
    private val vm:GroupsFragmentVewModel by viewModels()
    private lateinit var name:String
    private var position:Int=0
    private lateinit var adapter:DetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGroupsBinding.inflate(inflater,container,false)



        adapter = DetailsAdapter(object : OnItemClickListener{
            override fun <T> onClick(name: T) {
                val bundle = Bundle()
                bundle.putSerializable("group",name as Group)
                findNavController().navigate(R.id.action_groupDetailFragment_to_studentListFragment,bundle)
            }
        })

        Log.d("name", "onCreateView: $name")

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.groups.collectLatest {
                adapter.setData(it)
            }
        }

        binding.rcvGroupsList.adapter=adapter

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments?.getString("course_name")!!
        position = arguments?.getInt("position")!!
        vm.getGroups(position,name)
    }

}