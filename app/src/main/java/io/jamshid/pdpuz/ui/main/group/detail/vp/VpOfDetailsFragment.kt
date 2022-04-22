package io.jamshid.pdpuz.ui.main.group.detail.vp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.databinding.VpOfDetailsFragmentBinding
import io.jamshid.pdpuz.ui.main.group.detail.GroupDetailViewModel

class VpOfDetailsFragment : Fragment() {


    private val vm: GroupDetailViewModel by viewModels()
    private var _binding: VpOfDetailsFragmentBinding? = null
    private val binding: VpOfDetailsFragmentBinding get() = _binding!!
    private lateinit var name:String
    private lateinit var pos:String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm.getAllGroupsByCourse(name)
        _binding = VpOfDetailsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}