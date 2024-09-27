package app.domath

interface Logger {
    fun log(s: String)
}

expect fun logger(): Logger
