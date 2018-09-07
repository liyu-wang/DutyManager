package kevinsong.com.data.shift

import android.arch.persistence.room.Entity

@Entity(tableName = "shifts")
data class Shift(val id: Int, val start: String? = null, var end: String? = null, var startLatitude: String? = null, var startLongitude: String? = null, var endLatitude: String? = null, var endLongitude: String? = null, var image: String? = null)