package kevinsong.com.dutymanager.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kevinsong.com.data.businessinfo.BusinessInfo
import kevinsong.com.data.businessinfo.BusinessInfoDao
import kevinsong.com.data.shift.Shift
import kevinsong.com.data.shift.ShiftDao
import kevinsong.com.dutymanager.BuildConfig


@Database(entities = [Shift::class, BusinessInfo::class], version = BuildConfig.VERSION_CODE, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getShiftDao(): ShiftDao
    abstract fun getBussnissInfoDao(): BusinessInfoDao
}