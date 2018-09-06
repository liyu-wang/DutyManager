package kevinsong.com.data.shift

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface ShiftDao {

    @Query("select * from shifts")
    fun getAllShfits(): Single<List<Shift>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveShifts(shifts: List<Shift>)
}