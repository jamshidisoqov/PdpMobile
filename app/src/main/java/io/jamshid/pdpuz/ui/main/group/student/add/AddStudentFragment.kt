package io.jamshid.pdpuz.ui.main.group.student.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.jamshid.pdpuz.R

class AddStudentFragment : Fragment() {

    companion object {
        fun newInstance() = AddStudentFragment()
    }

    private lateinit var viewModel: AddStudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_student_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddStudentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}