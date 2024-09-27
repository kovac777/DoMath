package app.domath


external class Console {
    fun log(s: String)
}

external val console: Console

class WasmJsLogger : Logger {
    override fun log(s: String) {
        console.log(s)
    }
}

actual fun logger(): Logger = WasmJsLogger()