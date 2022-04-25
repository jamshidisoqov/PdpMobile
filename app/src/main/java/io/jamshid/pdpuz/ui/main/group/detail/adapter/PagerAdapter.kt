package io.jamshid.pdpuz.ui.main.group.detail.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.jamshid.pdpuz.ui.main.group.detail.vp.GroupsFragment

class PagerAdapter(private val name: String, fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putString("course_name", name)
        bundle.putInt("position", position)
        val fragment = GroupsFragment()
        fragment.arguments = bundle
        return fragment

    }
}