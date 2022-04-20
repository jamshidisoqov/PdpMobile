package io.jamshid.pdpuz.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private var _binding:HomeFragmentBinding? = null
    val binding:HomeFragmentBinding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater,container,false)
        binding.apply {
            cntCv1.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_courseListFragment)
            }
            cntCv2.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_groupListFragment)
            }
            cntCv3.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_mentorListFragment)
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }



}