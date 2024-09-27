package app.domath.domain

import app.domath.domain.model.SearchColorData

interface GenerateSearchColorTaskUseCase {

    operator fun invoke(): SearchColorData

}