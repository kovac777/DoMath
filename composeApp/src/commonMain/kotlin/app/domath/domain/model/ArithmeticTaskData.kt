package app.domath.domain.model

data class ArithmeticTaskData(
    val question: String,
    val correctAnswer: Int,
    val answerOptions: List<Int>
)
