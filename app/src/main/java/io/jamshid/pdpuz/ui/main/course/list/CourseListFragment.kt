package io.jamshid.pdpuz.ui.main.course.list

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.course.Course
import io.jamshid.pdpuz.databinding.AddCourseDialogBinding
import io.jamshid.pdpuz.databinding.CourseListFragmentBinding
import io.jamshid.pdpuz.domain.interfases.OnItemClickListener
import io.jamshid.pdpuz.ui.main.course.list.adapters.CourseListAdapter
import io.jamshid.pdpuz.utils.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CourseListFragment : BaseFragment<CourseListViewModel>() {

    private val vm: CourseListViewModel by viewModels()
    private var _binding: CourseListFragmentBinding? = null
    private val binding: CourseListFragmentBinding get() = _binding!!
    private lateinit var adapter: CourseListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CourseListFragmentBinding.inflate(inflater, container, false)
        adapter = CourseListAdapter(object : OnItemClickListener{
            override fun <T> onClick(name: T) {
                vm.navigateTo(name as Course)
            }
        })
        setHasOptionsMenu(true)
        configActionBar("Kurslarimiz")
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.allCourse.collectLatest {
                adapter.setData(it)

            }
        }
        binding.rcvCourseList.adapter = adapter
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                openDialog()
            }
            android.R.id.home -> {
                findNavController().navigateUp()
            }

        }
        return true
    }

    private fun openDialog() {
        val addCourseDialogBinding = AddCourseDialogBinding.bind(
            LayoutInflater.from(context).inflate(R.layout.add_course_dialog, null, false)
        )
        val dialog = Dialog(requireContext())
        dialog.setContentView(addCourseDialogBinding.root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        addCourseDialogBinding.btnAddCourse.setOnClickListener {
            val name = addCourseDialogBinding.addCourseName.text.toString()
            val desc = addCourseDialogBinding.addCourseDesc.text.toString()
            if (name.isNotEmpty() && desc.isNotEmpty()) {
                val course = Course(name, desc)
                vm.addCourse(course)
                dialog.dismiss()
            } else {
                if (name.isEmpty())
                    addCourseDialogBinding.addCourseName.error = "Property not filled"
                if (desc.isEmpty())
                    addCourseDialogBinding.addCourseDesc.error = "Property not filled"
            }

        }
    }


}