package io.jamshid.pdpuz.ui.main.group.detail.vp

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.databinding.FragmentGroupsBinding
import io.jamshid.pdpuz.databinding.UpdateGroupDialogBinding
import io.jamshid.pdpuz.domain.interfases.OnCustomItemClickListener
import io.jamshid.pdpuz.ui.main.group.detail.vp.adapters.DetailsAdapter
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class GroupsFragment : Fragment() {

    private var _binding: FragmentGroupsBinding? = null
    private val binding: FragmentGroupsBinding get() = _binding!!
    private val vm: GroupsFragmentVewModel by viewModels()
    private lateinit var name: String
    private var position: Int = 0
    private lateinit var adapter: DetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGroupsBinding.inflate(inflater, container, false)



        adapter = DetailsAdapter(object : OnCustomItemClickListener<Group> {

            override fun onItemClick(name: Group) {
                val bundle = Bundle()
                bundle.putSerializable("group", name)
                findNavController().navigate(
                    R.id.action_groupDetailFragment_to_studentListFragment,
                    bundle
                )
            }

            override fun onEditItemClick(name: Group) {
                openUpdateDialog(name)
            }

            override fun onTrashItemClick(name: Group) {

            }

            override fun showItemClick(name: Group) {

            }
        })

        Log.d("name", "onCreateView: $name")

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.groups.collectLatest {
                adapter.setData(it)
            }
        }

        binding.rcvGroupsList.adapter = adapter

        return binding.root
    }

    private fun openUpdateDialog(name: Group) {
        val dialog = Dialog(requireContext())
        val bd = UpdateGroupDialogBinding.bind(
            layoutInflater.inflate(
                R.layout.update_group_dialog,
                null,
                false
            )
        )
        vm.getMentorsByCourse(name.courseId)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.mentors.collectLatest {
                val arr = arrayOfNulls<String>(it.size)
                repeat(it.size) { i ->
                    arr[i] = "${it[i].firstName} ${it[i].lastName}"
                    if (it[i].mentorId == name.mentorId) {
                        bd.edGroupMentorOfCourse.setText("${it[i].firstName} ${it[i].lastName}")
                    }
                    val adapter = ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        arr
                    )
                    bd.edGroupMentorOfCourse.setAdapter(adapter)
                }
            }
        }

        bd.updateGroupName.setText(name.groupName)

        bd.btnUpdateGroup.setOnClickListener {
            val groupName = bd.updateGroupName.text.toString()
            val mentorName = bd.edGroupMentorOfCourse.text.toString()
            if (groupName.isNotEmpty() && mentorName.isNotEmpty()) {
                vm.updateGroup(
                    Group(
                        groupName,
                        name.courseId,
                        vm.getMentorId(mentorName),
                        name.startTime,
                        name.endTime,
                        name.days,
                        name.isOpened
                    )
                )
            }
        }
        dialog.setContentView(bd.root)
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments?.getString("course_name")!!
        position = arguments?.getInt("position")!!
        vm.getGroups(position, name)
    }

}