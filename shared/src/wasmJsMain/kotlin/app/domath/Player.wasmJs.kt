package app.domath

import kotlinx.browser.document
import org.w3c.dom.HTMLAudioElement

class WasmJsPlayer : Player {
    private val audioElement: HTMLAudioElement = document.createElement("audio") as HTMLAudioElement

    override fun play(uri: String) {
        audioElement.src = uri
        audioElement.play()
    }

    override fun stop() {
        audioElement.pause() // Остановка воспроизведения
        audioElement.currentTime = 0.0 // Сброс воспроизведения на начало
    }

    override fun release() {
        audioElement.pause() // Остановка аудио
        audioElement.src = "" // Очистка источника для освобождения ресурсов
    }
}

// Функция для получения экземпляра плеера
actual fun getPlayer(): Player = WasmJsPlayer()
