package kevinsong.com.data.businessinfo

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface BusinessInfoDao {
    @Query("select * from business_info limit 1")
    fun getBusinessInfo(): Maybe<BusinessInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBusinessInfo(businessInfo: BusinessInfo)
}