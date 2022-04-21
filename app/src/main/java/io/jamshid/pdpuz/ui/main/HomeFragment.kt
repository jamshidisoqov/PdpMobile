package io.jamshid.pdpuz.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import io.jamshid.pdpuz.MainActivity
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.databinding.HomeFragmentBinding
import io.jamshid.pdpuz.utils.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel>() {

    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding get() = _binding!!
    private val vm:HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            cntCv1.setOnClickListener {
                (super.requireActivity() as MainActivity).navController.navigate(R.id.action_homeFragment_to_courseListFragment)
            }
            cntCv2.setOnClickListener {
                (super.requireActivity() as MainActivity).navController.navigate(R.id.action_homeFragment_to_groupListFragment)
            }
            cntCv3.setOnClickListener {
                (super.requireActivity() as MainActivity).navController.navigate(R.id.action_homeFragment_to_mentorListFragment)
            }
        }

        super.configActionBar("Home",false)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override val viewModel: HomeViewModel
        get() = vm
    override val isNavigateBack: Boolean
        get() = false


}