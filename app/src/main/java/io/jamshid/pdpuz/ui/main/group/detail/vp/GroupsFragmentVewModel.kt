package io.jamshid.pdpuz.ui.main.group.detail.vp

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jamshid.pdpuz.data.local.dao.PdpDao
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.data.local.entities.mentor.Mentor
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
    private val inActiveGroupsByCourse: InActiveGroupsByCourse,
    private val pdpDao: PdpDao
) : BaseViewModel() {

    private var _groups: MutableStateFlow<List<Group>> = MutableStateFlow(emptyList())
    val groups: StateFlow<List<Group>> get() = _groups


    private var _mentors: MutableStateFlow<List<Mentor>> = MutableStateFlow(emptyList())
    val mentors: StateFlow<List<Mentor>> get() = _mentors


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

    fun getMentorsByCourse(name: String) {
        viewModelScope.launch {
            _mentors.value = pdpDao.getMentorsByCourse(name)
        }
    }

    fun updateGroup(group: Group) {
        viewModelScope.launch {
            pdpDao.updateGroup(group)
        }
    }

    fun getMentorId(name: String): Int {
        mentors.value.forEach {
            if ("${it.firstName} ${it.lastName}" == name) {
                return it.mentorId
            }
        }
        return 0
    }


}