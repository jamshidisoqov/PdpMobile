package io.jamshid.pdpuz.ui.main.group.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.databinding.GroupListFragmentBinding
import io.jamshid.pdpuz.ui.main.course.list.adapters.CourseListAdapter
import io.jamshid.pdpuz.utils.base.BaseFragment
import javax.inject.Inject

class GroupListFragment : BaseFragment<GroupListViewModel>() {


    private val vm: GroupListViewModel by viewModels()
    private var _binding: GroupListFragmentBinding? = null
    private val binding: GroupListFragmentBinding get() = _binding!!
    private lateinit var adapter: CourseListAdapter
    private val isNavigate:Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = GroupListFragmentBinding.inflate(inflater, container, false)
        adapter = CourseListAdapter()
        binding.rcvGroupList.adapter = adapter
        super.configActionBar("Group",false)

        configActionBar("Group", false)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override val isNavigateBack: Boolean
        get() = isNavigate

    override val viewModel: GroupListViewModel
        get() = vm


}