package io.jamshid.pdpuz.ui.main.mentor.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.course.Course
import io.jamshid.pdpuz.databinding.MentorDetailFragmentBinding
import io.jamshid.pdpuz.databinding.MentorListFragmentBinding
import io.jamshid.pdpuz.domain.interfases.OnItemClickListener
import io.jamshid.pdpuz.ui.main.mentor.list.adapters.MentorsAdapter
import io.jamshid.pdpuz.utils.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MentorListFragment : BaseFragment<MentorListViewModel>() {


    private var _binding: MentorListFragmentBinding? = null
    private val binding: MentorListFragmentBinding get() = _binding!!
    private lateinit var adapter: MentorsAdapter
    private val vm: MentorListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MentorListFragmentBinding.inflate(inflater, container, false)

        vm.getAllCourses()


        adapter = MentorsAdapter(object : OnItemClickListener {
            override fun <T> onClick(name: T) {
                vm.navigateTo(name as Course)
            }
        })

        configActionBar("Kurslar")

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.allCourses.collectLatest {
                adapter.setData(it)
            }
        }

        binding.rcvMentorList.adapter = adapter

        setHasOptionsMenu(true)

        return binding.root
    }

    override val viewModel: MentorListViewModel
        get() = vm
    override val isNavigateBack: Boolean
        get() = true


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_no,menu)
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

}