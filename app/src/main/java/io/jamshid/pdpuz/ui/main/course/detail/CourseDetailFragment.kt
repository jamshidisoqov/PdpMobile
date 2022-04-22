package io.jamshid.pdpuz.ui.main.course.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.utils.base.BaseFragment


@AndroidEntryPoint
class CourseDetailFragment : BaseFragment<CourseDetailViewModel>() {



    private val vm:CourseDetailViewModel by viewModels()
    private val args:CourseDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         setHasOptionsMenu(true)
        Toast.makeText(context, "${args.course}", Toast.LENGTH_SHORT).show()

        return inflater.inflate(R.layout.course_detail_fragment, container, false)
    }

    override val viewModel: CourseDetailViewModel
        get() = vm
    override val isNavigateBack: Boolean
        get() = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_no,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                findNavController().navigateUp()
            }
        }
        return true
    }


}