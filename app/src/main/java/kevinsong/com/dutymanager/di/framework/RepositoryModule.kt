package kevinsong.com.insighttimerdemo.module

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
class RepositoryModule {

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