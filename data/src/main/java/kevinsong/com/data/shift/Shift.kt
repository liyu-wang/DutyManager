package kevinsong.com.data.shift

import android.arch.persistence.room.Entity

@Entity(tableName = "shifts")
data class Shift(val id: Int, val start: String, var end: String, var startLatitude: String, var startLongitude: String, var endLatitude: String, var endLongitude: String, var image: String)