package io.jamshid.pdpuz.ui.main.mentor.add

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jamshid.pdpuz.data.local.dao.PdpDao
import io.jamshid.pdpuz.data.local.entities.mentor.Mentor
import io.jamshid.pdpuz.utils.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMentorViewModel @Inject constructor(private val pdpDao: PdpDao) : BaseViewModel() {

    fun addMentor(mentor: Mentor) {
        viewModelScope.launch {
            pdpDao.insertMentor(mentor)
        }
    }
}