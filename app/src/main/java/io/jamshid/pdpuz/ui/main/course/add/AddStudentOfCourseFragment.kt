package io.jamshid.pdpuz.ui.main.course.add

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.student.Student
import io.jamshid.pdpuz.databinding.AddStudentOfCourseFragmentBinding
import io.jamshid.pdpuz.utils.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class AddStudentOfCourseFragment : BaseFragment<AddStudentOfCourseViewModel>() {


    private var _binding: AddStudentOfCourseFragmentBinding? = null
    private val binding: AddStudentOfCourseFragmentBinding get() = _binding!!
    private val vm: AddStudentOfCourseViewModel by viewModels()
    private val args: AddStudentOfCourseFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        vm.getAllGroups(args.course.courseName)


        _binding = AddStudentOfCourseFragmentBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        vm.getAllMentors(args.course.courseName)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.allGroups.collectLatest {
                val arr = arrayOfNulls<String>(it.size)
                repeat(it.size) { i ->
                    arr[i] = it[i].groupName
                }
                val adapter =
                    ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, arr)
                binding.edGroupNameOfCourse.setAdapter(adapter)
            }

        }

        binding.edDateStudentOfCourse.setOnClickListener {
            openDialog()
        }


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

            vm.allMentors.collectLatest {
                Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                val arr = arrayOfNulls<String>(it.size)
                repeat(it.size) { i ->
                    arr[i] = "${it[i].firstName} ${it[i].lastName}"
                }
                val adapter =
                    ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, arr)

                binding.edGroupMentorOfCourse.setAdapter(adapter)
            }
        }

        binding.btnSaveStudent.setOnClickListener {
            val name: String = binding.edNameStudentCourse.text.toString()
            val surname: String = binding.edSurnameStudentCourse.text.toString()
            val fatherName: String = binding.edFatherNameStudentCourse.text.toString()
            val mentorName: String = binding.edGroupMentorOfCourse.text.toString()
            val groupName: String = binding.edGroupNameOfCourse.text.toString()
            val date: String = binding.edDateStudentOfCourse.text.toString()

            if (name.isNotEmpty() && surname.isNotEmpty() && fatherName.isNotEmpty() &&
                mentorName.isNotEmpty() && groupName.isNotEmpty() && date.isNotEmpty()
            ) {
                vm.addStudent(Student(name, surname, fatherName, date, groupName, 0))
            }
        }

        return binding.root
    }

    override val viewModel: AddStudentOfCourseViewModel
        get() = vm
    override val isNavigateBack: Boolean
        get() = true


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun openDialog() {
        val dialog = DatePickerDialog(requireContext())
        dialog.setOnDateSetListener { _, year, month, dayOfMonth ->
            val m = if ("$month".length < 2) "0$month" else "$month"
            val d = if ("$dayOfMonth".length < 2) "0$dayOfMonth" else "$dayOfMonth"
            binding.edDateStudentOfCourse.setText("$d,$m,$year")
        }
        dialog.show()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_no, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigateUp()
        return true
    }


}