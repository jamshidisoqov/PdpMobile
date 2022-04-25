package io.jamshid.pdpuz.ui.main.course.add

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jamshid.pdpuz.data.local.dao.PdpDao
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.data.local.entities.mentor.Mentor
import io.jamshid.pdpuz.data.local.entities.student.Student
import io.jamshid.pdpuz.utils.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddStudentOfCourseViewModel @Inject constructor(private val pdpDao: PdpDao): BaseViewModel() {



    //group
    private var _allGroups:MutableStateFlow<List<Group>> = MutableStateFlow(emptyList())
    val allGroups:StateFlow<List<Group>> get() = _allGroups
    //mentor
    private var _allMentors:MutableStateFlow<List<Mentor>> = MutableStateFlow(emptyList())
    val allMentors:StateFlow<List<Mentor>> get() = _allMentors

    fun getAllGroups(courseName:String){
        viewModelScope.launch {
            _allGroups.value = pdpDao.getGroupsByCourse(courseName)
        }
    }

    fun getAllMentors(courseName: String){
        viewModelScope.launch {
            _allMentors.value = pdpDao.getMentorsByCourse(courseName)
        }
    }

    fun addStudent(student: Student){
        viewModelScope.launch {
            pdpDao.insertStudent(student)
        }
    }


}