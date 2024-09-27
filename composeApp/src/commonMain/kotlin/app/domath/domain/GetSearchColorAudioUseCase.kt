package app.domath.domain

import app.domath.domain.model.Color

interface GetSearchColorAudioUseCase {
    suspend operator fun invoke(color: Color): String
}