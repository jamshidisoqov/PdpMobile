package io.jamshid.pdpuz.ui.main.course.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jamshid.pdpuz.data.local.entities.course.Course
import io.jamshid.pdpuz.domain.models.Resourse
import io.jamshid.pdpuz.domain.usecases.AddCourse
import io.jamshid.pdpuz.domain.usecases.GetCoursesUseCase
import io.jamshid.pdpuz.utils.base.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseListViewModel @Inject constructor(
    private val addCourse: AddCourse,
    private val getCoursesUseCase: GetCoursesUseCase
) : BaseViewModel() {

    private var _allCourse: MutableStateFlow<List<Course>> = MutableStateFlow(emptyList())
    val allCourse: StateFlow<List<Course>> get() = _allCourse

    fun navigateTo(course: Course) {
        navigate(CourseListFragmentDirections.actionCourseListFragmentToCourseDetailFragment(course))
    }

    fun addCourse(course: Course) {
        viewModelScope.launch {
            addCourse.invoke(course)
            getCourses()
        }
    }

    init {
       getCourses()
    }

    private fun getCourses(){
        viewModelScope.launch {
            val list = getCoursesUseCase.invoke()
                _allCourse.value = list
            }
        }
    }


