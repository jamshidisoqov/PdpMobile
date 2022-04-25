package io.jamshid.pdpuz.ui.main.group.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.databinding.GroupDetailFragmentBinding
import io.jamshid.pdpuz.ui.main.group.detail.adapter.PagerAdapter
import io.jamshid.pdpuz.utils.base.BaseFragment

class GroupDetailFragment : BaseFragment<GroupDetailViewModel>() {

    private var _binding: GroupDetailFragmentBinding? = null
    private val binding: GroupDetailFragmentBinding get() = _binding!!
    private val isNavigate: Boolean = true
    private val vm: GroupDetailViewModel by viewModels()
    private val args: GroupDetailFragmentArgs by navArgs()
    private lateinit var adapter: PagerAdapter

    private val tab_titles = arrayOf("Ochilgan Guruhlar", "Ochilayotgan Guruhlar")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = GroupDetailFragmentBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        configActionBar("Guruhlar")

        binding.vp2Group.currentItem = 0

        adapter = PagerAdapter(args.course.courseName, requireActivity())

        binding.vp2Group.adapter = adapter

        binding.vp2Group.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0)
                    setHasOptionsMenu(false)
                else {
                    setHasOptionsMenu(true)
                }
            }
        })



        TabLayoutMediator(binding.tabGroup, binding.vp2Group) { tab, pos ->
            tab.text = tab_titles[pos]
        }.attach()






        return binding.root
    }


    override val viewModel: GroupDetailViewModel
        get() = vm
    override val isNavigateBack: Boolean
        get() = isNavigate


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu, menu)
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

    override fun onResume() {
        super.onResume()
        binding.vp2Group.currentItem = 0
    }

}