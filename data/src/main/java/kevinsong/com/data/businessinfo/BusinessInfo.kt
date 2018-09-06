package kevinsong.com.data.businessinfo

import android.arch.persistence.room.Entity

@Entity(tableName = "business_info")
data class BusinessInfo(val name: String, val logo: String)