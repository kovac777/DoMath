package app.domath.domain.impl

import app.domath.domain.GenerateArithmeticTaskUseCase
import app.domath.domain.model.ArithmeticTaskData

internal class GenerateArithmeticTaskUseCaseImpl : GenerateArithmeticTaskUseCase {
    override suspend fun invoke(): ArithmeticTaskData {
        val number1 = (0..100).random()
        val number2 = (0..100).random()
        val isAddition = (0..1).random() == 0

        val question = if (isAddition) {
            "$number1 + $number2"
        } else {
            "$number1 - $number2"
        }

        val correctAnswer = if (isAddition) {
            number1 + number2
        } else {
            number1 - number2
        }

        // Генерация 3 случайных неправильных ответов
        val incorrectAnswers = mutableSetOf<Int>()
        while (incorrectAnswers.size < 3) {
            val randomAnswer = (correctAnswer - 10..correctAnswer + 10).random()
            if (randomAnswer != correctAnswer) {
                incorrectAnswers.add(randomAnswer)
            }
        }

        // Создание списка из правильного и неправильных ответов и перемешивание
        val answerOptions = (incorrectAnswers + correctAnswer).shuffled()

        return ArithmeticTaskData(question, correctAnswer, answerOptions)
    }
}