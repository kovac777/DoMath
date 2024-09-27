package app.domath.data.provider.audio

interface AudioProvider {
    suspend fun provide(path: String): String
}