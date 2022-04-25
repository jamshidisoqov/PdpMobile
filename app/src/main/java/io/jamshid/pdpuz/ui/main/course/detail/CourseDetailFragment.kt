package io.jamshid.pdpuz.ui.main.course.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.databinding.CourseDetailFragmentBinding
import io.jamshid.pdpuz.ui.main.group.detail.GroupDetailFragmentDirections
import io.jamshid.pdpuz.utils.base.BaseFragment


@AndroidEntryPoint
class CourseDetailFragment : BaseFragment<CourseDetailViewModel>() {


    private var _binding: CourseDetailFragmentBinding? = null
    private val binding: CourseDetailFragmentBinding get() = _binding!!
    private val vm: CourseDetailViewModel by viewModels()
    private val args: CourseDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = CourseDetailFragmentBinding.inflate(inflater, container, false)

        configActionBar(args.course.courseName)

        binding.apply {
            tvCourseInfo.text = args.course.courseDesc
            btnCourseAddStudent.setOnClickListener {
                findNavController().navigate(
                    CourseDetailFragmentDirections.actionCourseDetailFragmentToAddStudentOfCourseFragment(
                        args.course
                    )
                )
            }
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    override val viewModel: CourseDetailViewModel
        get() = vm
    override val isNavigateBack: Boolean
        get() = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_no, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigateUp()
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}