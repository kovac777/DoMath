package app.domath

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform