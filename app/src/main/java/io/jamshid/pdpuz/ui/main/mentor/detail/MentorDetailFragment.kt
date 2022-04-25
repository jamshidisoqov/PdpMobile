package io.jamshid.pdpuz.ui.main.mentor.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.databinding.MentorDetailFragmentBinding
import io.jamshid.pdpuz.ui.main.mentor.detail.adapters.MentorListAdapter
import io.jamshid.pdpuz.utils.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MentorDetailFragment : BaseFragment<MentorDetailViewModel>() {

    private var _binding: MentorDetailFragmentBinding? = null
    private val binding: MentorDetailFragmentBinding get() = _binding!!
    private val vm: MentorDetailViewModel by viewModels()
    private val args: MentorDetailFragmentArgs by navArgs()
    private lateinit var adapter: MentorListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        adapter = MentorListAdapter()

        setHasOptionsMenu(true)

        _binding = MentorDetailFragmentBinding.inflate(inflater, container, false)

        vm.getAllMentors(args.course.courseName)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.mentorList.collectLatest {
                adapter.setData(it)
            }
        }

        binding.rcvCourseList.adapter=adapter

        configActionBar(args.course.courseName)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.add -> {
                MentorDetailFragmentDirections.actionMentorDetailFragmentToAddMentorFragment(args.course.courseName)
                findNavController().navigate(R.id.action_mentorDetailFragment_to_addMentorFragment)
            }
            android.R.id.home -> {
                findNavController().navigateUp()
            }
        }

        return true
    }

    override val viewModel: MentorDetailViewModel
        get() = vm
    override val isNavigateBack: Boolean
        get() = true


}