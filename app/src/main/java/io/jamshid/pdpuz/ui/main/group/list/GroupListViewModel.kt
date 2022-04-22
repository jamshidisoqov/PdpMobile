package io.jamshid.pdpuz.ui.main.group.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jamshid.pdpuz.data.local.entities.course.Course
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.domain.models.Resourse
import io.jamshid.pdpuz.domain.usecases.GetGroupsUseCase
import io.jamshid.pdpuz.utils.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupListViewModel @Inject constructor(private val getGroupsUseCase: GetGroupsUseCase):  BaseViewModel() {

    private var _allGroup: MutableSharedFlow<Resourse<List<Group>>> = MutableSharedFlow()
    val allGroup:SharedFlow<Resourse<List<Group>>> get() = _allGroup

     fun navigateTo(course:Course) {
        navigate(GroupListFragmentDirections.actionGroupListFragmentToGroupDetailFragment(course))
     }

    init {
        viewModelScope.launch {
          getGroupsUseCase.invoke().collectLatest {
              _allGroup.emit(it)
          }
        }
    }


}