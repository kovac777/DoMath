package app.domath

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.domath.presentation.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DoMath",
    ) {
        App()
    }
}