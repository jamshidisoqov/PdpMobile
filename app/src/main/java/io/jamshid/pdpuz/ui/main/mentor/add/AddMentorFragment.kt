package io.jamshid.pdpuz.ui.main.mentor.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.jamshid.pdpuz.R
import io.jamshid.pdpuz.data.local.entities.mentor.Mentor
import io.jamshid.pdpuz.databinding.AddMentorFragmentBinding
import io.jamshid.pdpuz.ui.main.mentor.detail.MentorDetailFragmentDirections
import io.jamshid.pdpuz.utils.base.BaseFragment

@AndroidEntryPoint
class AddMentorFragment : BaseFragment<AddMentorViewModel>() {

    private var _binding: AddMentorFragmentBinding? = null
    private val binding: AddMentorFragmentBinding get() = _binding!!
    private val args: AddMentorFragmentArgs by navArgs()
    private val vm: AddMentorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = AddMentorFragmentBinding.inflate(inflater, container, false)

        configActionBar("Add Mentor")

        setHasOptionsMenu(true)

        binding.btnAddMentor.setOnClickListener {

            binding.apply {
                val name: String = edMentorName.text.toString()
                val lastName: String = edMentorSurname.text.toString()
                val middleName: String = edMentorFatherName.text.toString()
                if (name.isNotEmpty() && lastName.isNotEmpty() && middleName.isNotEmpty()) {
                    vm.addMentor(Mentor(0, name, lastName, middleName, args.courseName))
                } else {
                    Snackbar.make(
                        binding.btnAddMentor,
                        "Property has not filled",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                edMentorName.setText("")
                edMentorFatherName.setText("")
                edMentorSurname.setText("")
            }


        }

        return binding.root
    }


    override val viewModel: AddMentorViewModel
        get() = vm
    override val isNavigateBack: Boolean
        get() = true

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_no, menu)
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
}