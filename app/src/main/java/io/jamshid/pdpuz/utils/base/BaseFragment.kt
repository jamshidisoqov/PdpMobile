package io.jamshid.pdpuz.utils.base

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.jamshid.pdpuz.R

abstract class BaseFragment<T>() : Fragment() {

    var viewModel:T?=null


   open fun configActionBar(
        title: String,
        isAdded: Boolean
    ) {
       actionBarTitle(title)
        setHasOptionsMenu(isAdded)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add) Toast.makeText(context, "Jarayonda", Toast.LENGTH_SHORT).show()
        return true
    }
    fun actionBarTitle(title: String) {
        (activity as AppCompatActivity).supportActionBar?.apply {
            show()
            setTitle(title)
            setDisplayHomeAsUpEnabled(true)
        }
    }
}