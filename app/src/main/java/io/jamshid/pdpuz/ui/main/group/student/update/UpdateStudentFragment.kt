package io.jamshid.pdpuz.ui.main.group.student.update

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.jamshid.pdpuz.R

class UpdateStudentFragment : Fragment() {

    companion object {
        fun newInstance() = UpdateStudentFragment()
    }

    private lateinit var viewModel: UpdateStudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.update_student_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpdateStudentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}