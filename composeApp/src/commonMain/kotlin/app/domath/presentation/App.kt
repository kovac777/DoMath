package app.domath.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import app.domath.di.appModule
import app.domath.presentation.screen.menu.MainMenuScreen
import app.domath.presentation.screen.searchcolor.SearchColorScreen
import app.domath.presentation.screen.searchnumber.SearchNumberScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinApplication(application = {
            modules(appModule)
        }) {
            val navigationViewModel = viewModel { NavigationViewModel() }

            when (navigationViewModel.screen.collectAsState().value) {
                Navigation.MainMenu -> MainMenuScreen { navigationViewModel.navigateTo(it) }
                Navigation.SearchNumber -> SearchNumberScreen { navigationViewModel.popBackStack() }
                Navigation.SearchColor -> SearchColorScreen { navigationViewModel.popBackStack() }
            }
        }

    }
}