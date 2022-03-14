package local.kas.myapplication

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat


class SpyService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        Thread {
        val locationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager
        val locationListener: LocationListener = object : LocationListener {
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
            override fun onLocationChanged(location: Location) {

                val coordinates = "coordinates : ${location.latitude} ${location.longitude}"

                Log.d("ChoreographerKt", coordinates)

            }
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
//            Log.d("ChoreographerKt", "43")
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
//                Log.d("ChoreographerKt", "49")
                val allProviders = locationManager.allProviders
//                Log.d("ChoreographerKt", "51")
                if (allProviders.contains(LocationManager.NETWORK_PROVIDER)) {
                    locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, 0,
                        0f, locationListener
                    )
                }
                if (allProviders.contains(LocationManager.GPS_PROVIDER)) {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        3000,
                        0f,
                        locationListener
                    )
                }
//                Log.d("ChoreographerKt", "66")
            } else {
                Log.d("ChoreographerKt", "68")
            }
        } else {
            Log.d("ChoreographerKt", "71")
        }
//        }.start()  don't work
        return START_STICKY
    }
}