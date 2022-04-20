package io.jamshid.pdpuz.ui.main.group.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.databinding.GroupListFragmentBinding
import io.jamshid.pdpuz.ui.main.course.list.adapters.CourseListAdapter

class GroupListFragment : Fragment() {


    private val viewModel: GroupListViewModel by viewModels()
    private var _binding: GroupListFragmentBinding? = null
    private val binding: GroupListFragmentBinding get() = _binding!!
    private lateinit var adapter: CourseListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = GroupListFragmentBinding.inflate(inflater, container, false)

        adapter = CourseListAdapter()
        binding.rcvGroupList.adapter = adapter
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}