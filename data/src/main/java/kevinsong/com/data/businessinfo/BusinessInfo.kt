package kevinsong.com.data.businessinfo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "business_info")
data class BusinessInfo(@PrimaryKey val name: String, val logo: String)