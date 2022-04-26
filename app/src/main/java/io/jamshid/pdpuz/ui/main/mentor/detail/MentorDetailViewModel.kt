package io.jamshid.pdpuz.ui.main.mentor.detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jamshid.pdpuz.data.local.dao.PdpDao
import io.jamshid.pdpuz.data.local.entities.mentor.Mentor
import io.jamshid.pdpuz.utils.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentorDetailViewModel @Inject constructor(private val pdpDao: PdpDao) : BaseViewModel() {

    private var _mentorList:MutableStateFlow<List<Mentor>> = MutableStateFlow(emptyList())
    val mentorList:StateFlow<List<Mentor>> get() = _mentorList

    fun getAllMentors(courseName:String){
        viewModelScope.launch {
            _mentorList.value = pdpDao.getMentorsByCourse(courseName)
        }
    }

    fun updateMentor(mentor: Mentor){
        viewModelScope.launch {
            pdpDao.updateMentor(mentor)
        }
    }

    fun deleteMentor(mentor: Mentor){
        viewModelScope.launch {
            pdpDao.deleteMentor(mentor)
        }
    }


}