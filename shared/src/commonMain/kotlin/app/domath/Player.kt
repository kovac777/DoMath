package app.domath

interface Player {
    fun play(uri: String)
    fun stop()
    fun release()
}

expect fun getPlayer(): Player