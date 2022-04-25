package io.jamshid.pdpuz.ui.main.group.detail.vp

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.domain.usecases.ActiveGroupsByCourse
import io.jamshid.pdpuz.domain.usecases.InActiveGroupsByCourse
import io.jamshid.pdpuz.utils.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupsFragmentVewModel @Inject constructor(
    private val activeGroupsByCourse: ActiveGroupsByCourse,
    private val inActiveGroupsByCourse: InActiveGroupsByCourse
) : BaseViewModel() {

    private var _groups: MutableStateFlow<List<Group>> = MutableStateFlow(emptyList())
    val groups: StateFlow<List<Group>> get() = _groups

    fun getGroups(pos: Int, name: String) {
        if (pos == 0) {
            viewModelScope.launch {
                _groups.value = activeGroupsByCourse.invoke(name)
                Log.d(TAG, "getGroups: keldi")
            }
        } else {
            viewModelScope.launch {
                _groups.value = inActiveGroupsByCourse.invoke(name)
            }
        }
    }

}