package kevinsong.com.dutymanager

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient


class LocationProvider {
    val location = MutableLiveData<Location?>()
    @SuppressLint("MissingPermission")
    fun getLocation(context: Context) {
        FusedLocationProviderClient(context).lastLocation.addOnCompleteListener {
            if (it.isSuccessful) {
                location.value = it.result
            } else {
                location.value = null
            }
        }
    }

}