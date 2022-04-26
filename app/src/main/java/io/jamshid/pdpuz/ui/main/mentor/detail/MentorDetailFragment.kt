package io.jamshid.pdpuz.ui.main.mentor.detail

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.mentor.Mentor
import io.jamshid.pdpuz.databinding.MentorDetailFragmentBinding
import io.jamshid.pdpuz.databinding.UpdateMentorDialogBinding
import io.jamshid.pdpuz.domain.interfases.OnCustomItemClickListener
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


        adapter = MentorListAdapter(object : OnCustomItemClickListener<Mentor> {

            override fun onEditItemClick(name: Mentor) {
                openUpdateDialog(name)
            }

            override fun onTrashItemClick(name: Mentor) {
                openDeleteDialog(name)
            }

            override fun showItemClick(name: Mentor) {

            }

        })

        setHasOptionsMenu(true)

        _binding = MentorDetailFragmentBinding.inflate(inflater, container, false)

        vm.getAllMentors(args.course.courseName)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.mentorList.collectLatest {
                adapter.setData(it)
            }
        }

        binding.rcvCourseList.adapter = adapter

        configActionBar(args.course.courseName)

        return binding.root
    }

    private fun openDeleteDialog(name: Mentor) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setMessage("Are you sure?")
        dialog.setTitle("Delete")
        dialog.setPositiveButton(
            "Yes"
        ) { p0, p1 ->
            vm.deleteMentor(name)
            vm.getAllMentors(args.course.courseName)
        }
        dialog.setNegativeButton("No") { p0, p1 ->
            dialog.create().dismiss()
        }
        dialog.show()

    }

    private fun openUpdateDialog(name: Mentor) {
        val dialog = Dialog(requireContext())
        val update = UpdateMentorDialogBinding.bind(
            layoutInflater.inflate(
                R.layout.update_mentor_dialog,
                null,
                false
            )
        )
        update.apply {
            updateMentorFirstName.setText(name.firstName)
            updateMentorLastName.setText(name.lastName)
            updateMentorMiddleName.setText(name.middleName)
        }
        dialog.setContentView(update.root)
        dialog.show()

        update.btnUpdateMentor.setOnClickListener {
            val lastName = update.updateMentorLastName.text.toString()
            val firstName = update.updateMentorFirstName.text.toString()
            val middleName = update.updateMentorMiddleName.text.toString()
            if (lastName.isNotEmpty() && firstName.isNotEmpty() && middleName.isNotEmpty()) {
                vm.updateMentor(
                    Mentor(
                        name.mentorId,
                        firstName,
                        lastName,
                        middleName,
                        name.courseId
                    )
                )
            }
        }
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