package app.domath.domain.impl

import app.domath.data.provider.audio.AudioProvider
import app.domath.domain.GetCongratulationAudioUseCase

class GetCongratulationAudioUseCaseImpl(
    private val audioProvider: AudioProvider,
) : GetCongratulationAudioUseCase {
    val paths = listOf(
        "files/well_done.wav",
    )

    override suspend fun invoke(): String = audioProvider.provide(paths.random())
}