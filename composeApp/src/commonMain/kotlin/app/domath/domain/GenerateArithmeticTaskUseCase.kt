package app.domath.domain

import app.domath.domain.model.ArithmeticTaskData

interface GenerateArithmeticTaskUseCase {
    suspend operator fun invoke(): ArithmeticTaskData
}