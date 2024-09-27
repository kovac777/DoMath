package app.domath.domain.impl

import app.domath.data.provider.audio.AudioProvider
import app.domath.domain.GetFailedAudioUseCase

class GetFailedAudioUseCaseImpl(
    private val audioProvider: AudioProvider,
) : GetFailedAudioUseCase {
    val paths = listOf(
        "files/think_again.wav",
        "files/try_again.wav",
    )

    override suspend fun invoke(): String = audioProvider.provide(paths.random())
}