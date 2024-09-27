package app.domath.domain

interface GetFailedAudioUseCase {
    suspend operator fun invoke(): String
}