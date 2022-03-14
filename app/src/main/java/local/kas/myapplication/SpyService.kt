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
                Log.d("ChoreographerKt", "31")
                val coordinates = "coordinates : ${location.latitude} ${location.longitude}"
                Log.d("ChoreographerKt", "33")
                Log.d("ChoreographerKt", coordinates)
                Log.d("ChoreographerKt", "35")
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("ChoreographerKt", "40")
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("ChoreographerKt", "42")
                val allProviders = locationManager.allProviders
                Log.d("ChoreographerKt", "44")
                if (allProviders.contains(LocationManager.NETWORK_PROVIDER)) {
                    locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        0,
                        0f,
                        locationListener
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
                Log.d("ChoreographerKt", "51")
            } else {
                Log.d("ChoreographerKt", "53")
            }
        } else {
            Log.d("ChoreographerKt", "56")
        }
//        }.start()  don't work
        return START_STICKY
    }
}