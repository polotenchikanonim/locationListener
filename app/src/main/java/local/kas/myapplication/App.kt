package local.kas.myapplication

import android.app.Application
import android.util.Log

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
//        @JvmStatic
//        val tag = "ChoreographerKt"
//        fun log(s: Int) {
//            Log.d(tag, s.toString())
//        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Log.d("ChoreographerKt", "application onCreate")
    }
}

val app: App by lazy {
    App.instance
}