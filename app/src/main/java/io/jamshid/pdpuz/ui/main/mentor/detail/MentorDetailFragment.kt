package io.jamshid.pdpuz.ui.main.mentor.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.jamshid.pdpuz.R

class MentorDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MentorDetailFragment()
    }

    private lateinit var viewModel: MentorDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mentor_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MentorDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}