package io.jamshid.pdpuz.ui.main.group.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TimePicker
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.databinding.AddGroupFragmentBinding
import io.jamshid.pdpuz.utils.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AddGroupFragment : BaseFragment<AddGroupViewModel>() {

    private var _binding: AddGroupFragmentBinding? = null
    private val binding: AddGroupFragmentBinding get() = _binding!!
    private val vm: AddGroupViewModel by viewModels()
    private val args: AddGroupFragmentArgs by navArgs()
    private var map: HashMap<String, Int> = HashMap()

    //course my mentor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        vm.getMentorsByCourse(args.courseName)

        _binding = AddGroupFragmentBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.mentorList.collectLatest {

                val arr = arrayOfNulls<String>(it.size)
                for (i in arr.indices) {
                    val k = "${it[i].firstName} ${it[i].lastName}"
                    arr[i] = k
                    map[k] = i
                }
                val adapter =
                    ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, arr)
                binding.actvMentorName.setAdapter(adapter)
            }
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.time)
        )

        binding.actvTime.setAdapter(adapter)
        binding.actvEndTime.setAdapter(adapter)


        binding.btnAddGroup.setOnClickListener {
            val groupName: String = binding.edGroupName.text.toString()
            val mentorName: String = binding.actvMentorName.text.toString()
            val time: String = binding.actvTime.text.toString()
            val endTime: String = binding.actvEndTime.text.toString()
            val date: String = binding.actvDate.text.toString()
            if (groupName.isNotEmpty() && mentorName.isNotEmpty() && time.isNotEmpty() && date.isNotEmpty()) {
                val group = Group(
                    groupName, args.courseName,
                    map[mentorName]!!, time, endTime, date, false
                )
                vm.addGroup(group)
                findNavController().navigateUp()
            }
        }

        return binding.root
    }

    override val viewModel: AddGroupViewModel
        get() = vm
    override val isNavigateBack: Boolean
        get() = true


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun openDateTimeDialog() {
        val dialog = TimePicker(requireContext())
    }

}