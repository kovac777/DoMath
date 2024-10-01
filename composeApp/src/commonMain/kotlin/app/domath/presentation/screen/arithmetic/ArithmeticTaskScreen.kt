package app.domath.presentation.screen.arithmetic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.domath.domain.GenerateArithmeticTaskUseCase
import app.domath.domain.GetCongratulationAudioUseCase
import app.domath.domain.GetFailedAudioUseCase
import app.domath.domain.model.ArithmeticTaskData
import app.domath.getPlayer
import domath.composeapp.generated.resources.Res
import domath.composeapp.generated.resources.ic_arrow_back
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun ArithmeticTaskScreen(
    onBackClicked: () -> Unit,
) {
    val generateArithmeticTaskUseCase = koinInject<GenerateArithmeticTaskUseCase>()
    val getCongratulationAudioUseCase = koinInject<GetCongratulationAudioUseCase>()
    val getFailedAudioUseCase = koinInject<GetFailedAudioUseCase>()
    val viewModel = viewModel {
        ArithmeticTaskViewModel(
            generateArithmeticTaskUseCase,
            getCongratulationAudioUseCase,
            getFailedAudioUseCase,
        )
    }

    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.generateTask()
        viewModel.action.collect {
            when (it) {
                is ArithmeticTaskAction.PlayAudio -> getPlayer().play(it.audio)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ArithmeticBoxes(viewModel, state.value.arithmeticTaskData)
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
fun ArithmeticBoxes(
    viewModel: ArithmeticTaskViewModel,
    arithmeticTaskData: ArithmeticTaskData?
) {
    if (arithmeticTaskData != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Отображение вопроса
            Text(
                text = arithmeticTaskData.question,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(16.dp)
            )

            // Отображение вариантов ответа
            arithmeticTaskData.answerOptions.forEach { answer ->
                Button(
                    onClick = {
                        viewModel.onAnswerClicked(answer)
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = answer.toString())
                }
            }
        }
    } else {
        // Показать индикатор загрузки, если задача ещё не сгенерирована
        CircularProgressIndicator()
    }
}
