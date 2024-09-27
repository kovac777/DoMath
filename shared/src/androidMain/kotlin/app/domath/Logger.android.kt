package app.domath

import android.util.Log

class AndroidLogger: Logger {

    override fun log(s: String) {
        Log.i(TAG, s)
    }

    companion object {
        private const val TAG = "AndroidLogger"
    }
}

actual fun logger(): Logger = AndroidLogger()