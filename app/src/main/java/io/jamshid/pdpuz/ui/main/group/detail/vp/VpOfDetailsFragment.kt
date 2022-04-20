package io.jamshid.pdpuz.ui.main.group.detail.vp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.jamshid.pdpuz.R

class VpOfDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = VpOfDetailsFragment()
    }

    private lateinit var viewModel: VpOfDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.vp_of_details_fragment, container, false)
    }



}