package app.domath.domain.impl

import app.domath.domain.GenerateSearchColorTaskUseCase
import app.domath.domain.model.Color
import app.domath.domain.model.SearchColorData

class GenerateSearchColorTaskUseCaseImpl : GenerateSearchColorTaskUseCase {

    override fun invoke(): SearchColorData {
        val colors = Color.entries.shuffled().take(4)
        val colorToFind = colors.random()
        return SearchColorData(colorToFind, colors)
    }

}