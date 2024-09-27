package app.domath.data.provider.audio.impl

import app.domath.data.provider.audio.AudioProvider
import domath.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

class AudioProviderImpl : AudioProvider {
    @OptIn(ExperimentalResourceApi::class)
    override suspend fun provide(path: String): String {
        return Res.getUri(path)
    }
}