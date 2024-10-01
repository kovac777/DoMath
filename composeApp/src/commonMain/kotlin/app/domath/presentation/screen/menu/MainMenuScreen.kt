package app.domath.presentation.screen.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.domath.presentation.Navigation
import app.domath.presentation.component.MainMenuButton
import domath.composeapp.generated.resources.Res
import domath.composeapp.generated.resources.main_menu_search_color
import domath.composeapp.generated.resources.main_menu_solve_example
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun MainMenuScreen(
    onMenuItemClicked: (Navigation) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        MainMenuButton(
//            onClick = { onMenuItemClicked(Navigation.SearchNumber) },
//            text = stringResource(Res.string.main_menu_search_number),
//        )
        MainMenuButton(
            onClick = { onMenuItemClicked(Navigation.SearchColor) },
            text = stringResource(Res.string.main_menu_search_color),
        )
        MainMenuButton(
            onClick = { onMenuItemClicked(Navigation.SolveExample) },
            text = stringResource(Res.string.main_menu_solve_example),
        )
    }
}