package app.domath.domain

interface GetCongratulationAudioUseCase {
    suspend operator fun invoke(): String
}