package kevinsong.com.insighttimerdemo.module

import dagger.Component
import kevinsong.com.dutymanager.di.viewmodel.ViewModelModule
import kevinsong.com.dutymanager.di.viewmodel.ViewModelFactoryModule
import kevinsong.com.dutymanager.shift.ShiftListActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ServiceModule::class, RepositoryModule::class, RoomModule::class, ViewModelFactoryModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(target: ShiftListActivity)
}