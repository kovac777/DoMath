package app.domath.presentation.screen.searchcolor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import app.domath.domain.GenerateSearchColorTaskUseCase
import app.domath.domain.GetCongratulationAudioUseCase
import app.domath.domain.GetFailedAudioUseCase
import app.domath.domain.GetSearchColorAudioUseCase
import app.domath.domain.model.SearchColorData
import app.domath.getPlayer
import domath.composeapp.generated.resources.Res
import domath.composeapp.generated.resources.ic_arrow_back
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun SearchColorScreen(
    onBackClicked: () -> Unit,
) {
    val generateSearchColorTaskUseCase = koinInject<GenerateSearchColorTaskUseCase>()
    val getSearchColorAudioUseCase = koinInject<GetSearchColorAudioUseCase>()
    val getCongratulationAudioUseCase = koinInject<GetCongratulationAudioUseCase>()
    val getFailedAudioUseCase = koinInject<GetFailedAudioUseCase>()
    val viewModel = viewModel {
        SearchColorViewModel(
            generateSearchColorTaskUseCase,
            getSearchColorAudioUseCase,
            getCongratulationAudioUseCase,
            getFailedAudioUseCase,
        )
    }

    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.generateTask()
        viewModel.action.collect {
            when (it) {
                is SearchColorAction.PlayAudio -> getPlayer().play(it.audio)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ColorBoxes(viewModel, state.value.searchColorData)
        IconButton(
            onClick = onBackClicked,
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_arrow_back),
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun ColorBoxes(viewModel: SearchColorViewModel, searchColorData: SearchColorData?) {
    searchColorData?.let {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .background(it.colors[0].toColor())
                        .weight(1f)
                        .aspectRatio(1f)
                        .clickable { viewModel.onColorClicked(it.colors[0]) }
                )
                Box(
                    modifier = Modifier
                        .background(it.colors[1].toColor())
                        .weight(1f)
                        .aspectRatio(1f)
                        .clickable { viewModel.onColorClicked(it.colors[1]) }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .background(it.colors[2].toColor())
                        .weight(1f)
                        .aspectRatio(1f)
                        .clickable { viewModel.onColorClicked(it.colors[2]) }
                )
                Box(
                    modifier = Modifier
                        .background(it.colors[3].toColor())
                        .weight(1f)
                        .aspectRatio(1f)
                        .clickable { viewModel.onColorClicked(it.colors[3]) }
                )
            }
        }
    }
}