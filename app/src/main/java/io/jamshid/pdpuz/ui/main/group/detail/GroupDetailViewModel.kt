package io.jamshid.pdpuz.ui.main.group.detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.domain.usecases.ActiveGroupsByCourse
import io.jamshid.pdpuz.domain.usecases.InActiveGroupsByCourse
import io.jamshid.pdpuz.ui.main.group.list.GroupListFragmentDirections
import io.jamshid.pdpuz.utils.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupDetailViewModel @Inject constructor(
    private val activeGroupsByCourse: ActiveGroupsByCourse,
    private val inActiveGroupsByCourse: InActiveGroupsByCourse
) : BaseViewModel() {

    private var _activeGroup: MutableStateFlow<List<Group>> = MutableStateFlow(emptyList())
    val activeGroup: StateFlow<List<Group>> get() = _activeGroup


    private var _inActiveGroup: MutableStateFlow<List<Group>> = MutableStateFlow(emptyList())
    val inActiveGroup: StateFlow<List<Group>> get() = _activeGroup


    fun navigateTo() {
        navigate(GroupDetailFragmentDirections.actionGroupDetailFragmentToAddGroupFragment())
    }

    fun getAllGroupsByCourse(name: String) {
        viewModelScope.launch {
            _activeGroup.value = activeGroupsByCourse.invoke(name)
            _inActiveGroup.value = inActiveGroupsByCourse.invoke(name)
        }
    }

}