package io.jamshid.pdpuz.ui.main.course.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.databinding.CourseListFragmentBinding
import io.jamshid.pdpuz.utils.base.BaseFragment

class CourseListFragment : BaseFragment<CourseListViewModel>() {

    private val vm: CourseListViewModel by viewModels()
    private var _binding: CourseListFragmentBinding? = null
    private val binding: CourseListFragmentBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CourseListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override val viewModel: CourseListViewModel
        get() = vm
    override val isNavigateBack: Boolean
        get() = true


}