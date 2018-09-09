package kevinsong.com.insighttimerdemo.module

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import kevinsong.com.data.businessinfo.BusinessInfoRepository
import kevinsong.com.data.businessinfo.BusinessInfoService
import kevinsong.com.data.shift.ShiftRepository
import kevinsong.com.data.shift.ShiftRepositoryImpl
import kevinsong.com.data.shift.ShiftService
import kevinsong.com.dutymanager.database.AppDatabase
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun providesRoomInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "duty_manager").build()
    }

    @Provides
    @Singleton
    fun provideShiftRepository(service: ShiftService, database: AppDatabase): ShiftRepository {
        return ShiftRepositoryImpl(service, database.getShiftDao())
    }

    @Provides
    @Singleton
    fun provideBussnissInfoRepository(service: BusinessInfoService, database: AppDatabase): BusinessInfoRepository {
        return BusinessInfoRepository(service, database.getBussnissInfoDao())
    }
}