package io.jamshid.pdpuz.utils.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import io.jamshid.pdpuz.R
import kotlinx.coroutines.flow.collectLatest

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    protected abstract val viewModel: VM

    private val directions:NavDirections?=null

    protected abstract val isNavigateBack: Boolean


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followNavigation()
    }

    private fun followNavigation() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.navigationCommand.collectLatest {
                handleNavigation(it)
            }
        }
    }

    private fun handleNavigation(navCommand: NavDirections) {
         findNavController().navigate(navCommand)
    }

    fun configActionBar(title: String, isAdded: Boolean) {
        actionBarTitle(title)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

     if (item.itemId == android.R.id.home) {
            findNavController().navigateUp()
        }
        return true
    }

    private fun actionBarTitle(title: String) {
        (activity as AppCompatActivity).supportActionBar?.apply {
            show()
            setTitle(title)
            setDisplayHomeAsUpEnabled(isNavigateBack)
        }
    }

}