package io.jamshid.pdpuz.ui.main.group.student.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jamshid.pdpuz.data.local.dao.PdpDao
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.data.local.entities.student.Student
import io.jamshid.pdpuz.utils.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StudentListViewModel @Inject constructor(private val pdpDao: PdpDao) : BaseViewModel() {

    private var _studentList: MutableStateFlow<List<Student>> = MutableStateFlow(emptyList())
    val studentList: StateFlow<List<Student>> get() = _studentList

    fun studentListByGroup(groupName:String){
        viewModelScope.launch {
            _studentList.value=pdpDao.getStudentsByGroup(groupName)
        }
    }

    fun updateGroup(group: Group){
        viewModelScope.launch {
            pdpDao.updateGroup(group)
        }
    }


}