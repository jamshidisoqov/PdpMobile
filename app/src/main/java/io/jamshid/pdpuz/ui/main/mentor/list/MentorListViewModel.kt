package io.jamshid.pdpuz.ui.main.mentor.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jamshid.pdpuz.data.local.entities.course.Course
import io.jamshid.pdpuz.domain.usecases.GetCoursesUseCase
import io.jamshid.pdpuz.utils.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentorListViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase
) : BaseViewModel() {


    private var _allCourses: MutableStateFlow<List<Course>> = MutableStateFlow(emptyList())
    val allCourses: StateFlow<List<Course>> get() = _allCourses

    fun navigateTo(course: Course) {
        navigate(MentorListFragmentDirections.actionMentorListFragmentToMentorDetailFragment(course))
    }


    fun getAllCourses(){
        viewModelScope.launch {
            _allCourses.value = getCoursesUseCase.invoke()
        }
    }

}