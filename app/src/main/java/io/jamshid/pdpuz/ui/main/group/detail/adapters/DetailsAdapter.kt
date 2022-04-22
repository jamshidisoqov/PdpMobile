package io.jamshid.pdpuz.ui.main.group.detail.adapters

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.ui.main.group.detail.vp.VpOfDetailsFragment
import java.util.ArrayList

class DetailsAdapter(fragmentManager: FragmentManager,private val name:String):FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
      val bundle = Bundle()
      bundle.putString("name",name)
      bundle.putInt("pos",position)
      val fragment = VpOfDetailsFragment()
        fragment.arguments=bundle
        return fragment
    }

}