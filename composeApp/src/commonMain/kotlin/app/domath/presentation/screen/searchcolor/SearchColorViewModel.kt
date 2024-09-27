package app.domath.presentation.screen.searchcolor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domath.domain.GenerateSearchColorTaskUseCase
import app.domath.domain.GetCongratulationAudioUseCase
import app.domath.domain.GetFailedAudioUseCase
import app.domath.domain.GetSearchColorAudioUseCase
import app.domath.domain.model.Color
import app.domath.domain.model.SearchColorData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchColorViewModel(
    private val generateSearchColorTaskUseCase: GenerateSearchColorTaskUseCase,
    private val getSearchColorAudioUseCase: GetSearchColorAudioUseCase,
    private val getCongratulationAudioUseCase: GetCongratulationAudioUseCase,
    private val getFailedAudioUseCase: GetFailedAudioUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchColorState())
    val state = _state.asStateFlow()

    private val _action = MutableSharedFlow<SearchColorAction>()
    val action = _action.asSharedFlow()

    fun generateTask() {
        viewModelScope.launch(Dispatchers.Default) {
            val searchColorData = generateSearchColorTaskUseCase.invoke()
            _state.update { it.copy(searchColorData = searchColorData) }
            val audio = getSearchColorAudioUseCase(searchColorData.colorToFind)
            _action.emit(SearchColorAction.PlayAudio(audio))
        }
    }

    fun onColorClicked(color: Color) {
        viewModelScope.launch(Dispatchers.Default) {
            if (color == _state.value.searchColorData?.colorToFind) {
                val audio = getCongratulationAudioUseCase()
                _action.emit(SearchColorAction.PlayAudio(audio))
                delay(2000)
                generateTask()
            } else {
                val audio = getFailedAudioUseCase()
                _action.emit(SearchColorAction.PlayAudio(audio))
            }
        }
    }

}

data class SearchColorState(
    val searchColorData: SearchColorData? = null,
)

sealed class SearchColorAction {
    data class PlayAudio(val audio: String) : SearchColorAction()
}