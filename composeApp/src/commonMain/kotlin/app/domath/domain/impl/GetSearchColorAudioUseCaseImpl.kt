package app.domath.domain.impl

import app.domath.data.provider.audio.AudioProvider
import app.domath.domain.GetSearchColorAudioUseCase
import app.domath.domain.model.Color

internal class GetSearchColorAudioUseCaseImpl(
    private val audioProvider: AudioProvider,
) : GetSearchColorAudioUseCase {

    override suspend fun invoke(color: Color): String = when (color) {
        Color.RED -> audioProvider.provide("files/red.wav")
        Color.GREEN -> audioProvider.provide("files/green.wav")
        Color.BLUE -> audioProvider.provide("files/blue.wav")
        Color.YELLOW -> audioProvider.provide("files/yellow.wav")
        Color.LIGHT_BLUE -> audioProvider.provide("files/light_blue.wav")
        Color.PINK -> audioProvider.provide("files/pink.wav")
        Color.ORANGE -> audioProvider.provide("files/orange.wav")
        Color.PURPLE -> audioProvider.provide("files/purple.wav")
        Color.BROWN -> audioProvider.provide("files/brown.wav")
        Color.GRAY -> audioProvider.provide("files/gray.wav")
        Color.DARK_GRAY -> audioProvider.provide("files/dark_gray.wav")
        Color.LIGHT_GRAY -> audioProvider.provide("files/light_gray.wav")
        Color.BLACK -> audioProvider.provide("files/black.wav")
        Color.WHITE -> audioProvider.provide("files/white.wav")
        Color.BEIGE -> audioProvider.provide("files/beige.wav")
    }

}