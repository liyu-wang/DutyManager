package kevinsong.com.data.shift

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "shifts")
data class Shift(@PrimaryKey val id: Int, val start: String? = null, var end: String? = null, var startLatitude: String? = null, var startLongitude: String? = null, var endLatitude: String? = null, var endLongitude: String? = null, var image: String? = null) : Serializable