package app.domath.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class NavigationViewModel : ViewModel() {

    private val _screen = MutableStateFlow<Navigation>(Navigation.MainMenu)
    val screen = _screen.asStateFlow()

    private val backStack = mutableListOf<Navigation>(Navigation.MainMenu)

    fun navigateTo(navigation: Navigation) {
        backStack.add(navigation)
        _screen.update { navigation }
    }

    fun popBackStack() {
        backStack.removeAt(backStack.lastIndex)
        _screen.update { backStack.last() }
    }

}

sealed class Navigation {
    data object MainMenu : Navigation()
    data object SearchNumber : Navigation()
    data object SearchColor : Navigation()
}