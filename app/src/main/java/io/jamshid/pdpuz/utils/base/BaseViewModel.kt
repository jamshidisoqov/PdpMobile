package io.jamshid.pdpuz.utils.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jamshid.pdpuz.ui.main.HomeFragmentDirections
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private var _navigationCommand: MutableSharedFlow<NavDirections> =
        MutableSharedFlow()
    val navigationCommand:SharedFlow<NavDirections> get() = _navigationCommand


    fun navigate(directions: NavDirections) {
        viewModelScope.launch {
            _navigationCommand.emit(directions)
        }
    }


}