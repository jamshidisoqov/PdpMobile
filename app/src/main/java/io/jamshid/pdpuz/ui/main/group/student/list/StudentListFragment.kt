package io.jamshid.pdpuz.ui.main.group.student.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.databinding.StudentListFragmentBinding
import io.jamshid.pdpuz.utils.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class StudentListFragment : BaseFragment<StudentListViewModel>() {

    private var _binding: StudentListFragmentBinding? = null
    private val binding: StudentListFragmentBinding get() = _binding!!
    private var args:Group? = null
    private val vm: StudentListViewModel by viewModels()
    private lateinit var adapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = StudentListFragmentBinding.inflate(inflater, container, false)

        args = arguments?.getSerializable("group") as Group

        configActionBar(args!!.groupName)

        vm.studentListByGroup(args!!.groupName)

        adapter = StudentAdapter()

        binding.apply {
            tvGroupNameStudentList.text = args!!.groupName
            tvGroupTimeByGroup.text = "${args!!.startTime}-${args!!.endTime}"
        }
        setHasOptionsMenu(true)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.studentList.collectLatest {
                adapter.setData(it)
                adapter.notifyDataSetChanged()
            }
        }

        binding.btnStartLessonByGroup.setOnClickListener {
            vm.updateGroup(args!!.copy(isOpened = true))
        }

        binding.rcvStudentList.adapter=adapter

        return binding.root
    }

    override val viewModel: StudentListViewModel
        get() = vm
    override val isNavigateBack: Boolean
        get() = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_no, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigateUp()
        return true
    }


}