package app.domath.presentation.component

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun MainMenuButton(
    onClick: () -> Unit,
    text: String,
) {
    Button(
        onClick = onClick,
    ) {
        Text(
            text = text,
        )
    }
}