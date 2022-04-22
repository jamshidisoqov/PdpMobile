package io.jamshid.pdpuz.ui.main.group.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.databinding.GroupDetailFragmentBinding
import io.jamshid.pdpuz.utils.base.BaseFragment

class GroupDetailFragment : BaseFragment<GroupDetailViewModel>() {

    private val args: GroupDetailFragmentArgs by navArgs()
    private var _binding: GroupDetailFragmentBinding? = null
    private val binding: GroupDetailFragmentBinding get() = _binding!!
    private val isNavigate: Boolean = true
    private val vm: GroupDetailViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GroupDetailFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        configActionBar("Guruhlar")
        return binding.root
    }


    override val viewModel: GroupDetailViewModel
        get() = vm
    override val isNavigateBack: Boolean
        get() = isNavigate


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
               vm.navigateTo()
            }
            android.R.id.home -> {
                findNavController().navigateUp()
            }
        }
        return true
    }



}