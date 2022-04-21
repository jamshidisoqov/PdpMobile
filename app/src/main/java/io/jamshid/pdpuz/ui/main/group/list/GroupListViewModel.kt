package io.jamshid.pdpuz.ui.main.group.list

import io.jamshid.pdpuz.utils.base.BaseViewModel

class GroupListViewModel : BaseViewModel() {

    override fun addMenu() {

    }

     fun <T:Any> navigateTo(name: T) {
        navigate(GroupListFragmentDirections.actionGroupListFragmentToGroupDetailFragment(aaa = name as String))
    }


}