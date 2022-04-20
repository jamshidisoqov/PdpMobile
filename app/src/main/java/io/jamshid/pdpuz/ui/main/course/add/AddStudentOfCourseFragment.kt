package io.jamshid.pdpuz.ui.main.course.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.jamshid.pdpuz.R

class AddStudentOfCourseFragment : Fragment() {

    companion object {
        fun newInstance() = AddStudentOfCourseFragment()
    }

    private lateinit var viewModel: AddStudentOfCourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_student_of_course_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddStudentOfCourseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}