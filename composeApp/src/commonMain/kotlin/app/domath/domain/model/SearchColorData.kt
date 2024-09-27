package app.domath.domain.model

data class SearchColorData(
    val colorToFind: Color,
    val colors: List<Color>,
)

enum class Color(val hex: Long) {
    RED(0xFFFF0000),          // Красный
    GREEN(0xFF00FF00),        // Зеленый
    BLUE(0xFF0000FF),         // Синий
    YELLOW(0xFFFFFF00),       // Желтый
    LIGHT_BLUE(0xFFADD8E6),   // Голубой
    PINK(0xFFFFC0CB),         // Розовый
    ORANGE(0xFFFFA500),       // Оранжевый
    PURPLE(0xFF800080),       // Фиолетовый
    BROWN(0xFFA52A2A),        // Коричневый
    GRAY(0xFF808080),         // Серый
    DARK_GRAY(0xFF404040),    // Темно-серый
    LIGHT_GRAY(0xFFD3D3D3),   // Светло-серый
    BLACK(0xFF000000),        // Черный
    WHITE(0xFFFFFFFF),        // Белый
    BEIGE(0xFFF5F5DC);        // Бежевый

    fun toColor() = androidx.compose.ui.graphics.Color(hex)
}