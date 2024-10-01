package app.domath.presentation.screen.arithmetic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domath.domain.GenerateArithmeticTaskUseCase
import app.domath.domain.GetCongratulationAudioUseCase
import app.domath.domain.GetFailedAudioUseCase
import app.domath.domain.model.ArithmeticTaskData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArithmeticTaskViewModel(
    private val generateArithmeticTaskUseCase: GenerateArithmeticTaskUseCase,
    private val getCongratulationAudioUseCase: GetCongratulationAudioUseCase,
    private val getFailedAudioUseCase: GetFailedAudioUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ArithmeticTaskState())
    val state = _state.asStateFlow()

    private val _action = MutableSharedFlow<ArithmeticTaskAction>()
    val action = _action.asSharedFlow()

    fun generateTask() {
        viewModelScope.launch(Dispatchers.Default) {
            val arithmeticTaskData = generateArithmeticTaskUseCase()
            _state.update { it.copy(arithmeticTaskData = arithmeticTaskData) }
        }
    }

    fun onAnswerClicked(answer: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            if (answer == _state.value.arithmeticTaskData?.correctAnswer) {
                val audio = getCongratulationAudioUseCase()
                _action.emit(ArithmeticTaskAction.PlayAudio(audio))
                delay(2000)
                generateTask()
            } else {
                val audio = getFailedAudioUseCase()
                _action.emit(ArithmeticTaskAction.PlayAudio(audio))
            }
        }
    }
}

data class ArithmeticTaskState(
    val arithmeticTaskData: ArithmeticTaskData? = null,
)

sealed class ArithmeticTaskAction {
    data class PlayAudio(val audio: String) : ArithmeticTaskAction()
}