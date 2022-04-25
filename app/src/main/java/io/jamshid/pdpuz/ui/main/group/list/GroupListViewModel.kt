package io.jamshid.pdpuz.ui.main.group.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jamshid.pdpuz.data.local.entities.course.Course
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.domain.models.Resourse
import io.jamshid.pdpuz.domain.usecases.GetCoursesUseCase
import io.jamshid.pdpuz.domain.usecases.GetGroupsUseCase
import io.jamshid.pdpuz.utils.base.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupListViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase) :
    BaseViewModel() {

    private var _allCourses: MutableStateFlow<List<Course>> = MutableStateFlow(emptyList())
    val allCourses: StateFlow<List<Course>> get() = _allCourses

    fun navigateTo(course: Course) {
        navigate(GroupListFragmentDirections.actionGroupListFragmentToGroupDetailFragment(course))
    }

    init {
        viewModelScope.launch {
            _allCourses.value = getCoursesUseCase.invoke()
        }
    }


}