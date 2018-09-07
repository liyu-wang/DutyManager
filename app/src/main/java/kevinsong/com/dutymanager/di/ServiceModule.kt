package kevinsong.com.insighttimerdemo.module

import dagger.Module
import dagger.Provides
import kevinsong.com.data.businessinfo.BusinessInfoService
import kevinsong.com.data.shift.ShiftService
import retrofit2.Retrofit

@Module
class ServiceModule {

    @Provides
    fun provideShiftService(retrofit: Retrofit): ShiftService {
        return retrofit.create(ShiftService::class.java)
    }

    @Provides
    fun provideBussinessInfoService(retrofit: Retrofit): BusinessInfoService {
        return retrofit.create(BusinessInfoService::class.java)
    }
}