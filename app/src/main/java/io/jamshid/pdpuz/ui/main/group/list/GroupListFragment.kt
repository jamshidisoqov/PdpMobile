package io.jamshid.pdpuz.ui.main.group.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.course.Course
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.databinding.GroupListFragmentBinding
import io.jamshid.pdpuz.domain.interfases.OnItemClickListener
import io.jamshid.pdpuz.ui.main.group.list.adapters.GroupListAdapter
import io.jamshid.pdpuz.utils.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class GroupListFragment : BaseFragment<GroupListViewModel>() {


    private val vm: GroupListViewModel by viewModels()
    private var _binding: GroupListFragmentBinding? = null
    private val binding: GroupListFragmentBinding get() = _binding!!
    private lateinit var adapter: GroupListAdapter
    private val isNavigate: Boolean = true
    var arrL = ArrayList<Group>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = GroupListFragmentBinding.inflate(inflater, container, false)
        adapter = GroupListAdapter(object : OnItemClickListener{
            override fun <T> onClick(name: T) {
                vm.navigateTo(name as Course)
            }
        })
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.allGroup.collectLatest {
                // adapter.differ.submitList(it.data)

                if (it.isLoading)
                    showProgress()
                else hideProgress()
            }
        }
        configActionBar("Kurslar")
        binding.rcvGroupList.adapter = adapter

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu,menu)
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

    override val isNavigateBack: Boolean
        get() = isNavigate

    override val viewModel: GroupListViewModel
        get() = vm


}