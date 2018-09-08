package kevinsong.com.dutymanager.di.viewmodel

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kevinsong.com.dutymanager.bussinessinfo.InfoViewModel
import kevinsong.com.dutymanager.shift.ShiftViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShiftViewModel::class)
    abstract fun bindShiftViewModel(shiftViewModel: ShiftViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InfoViewModel::class)
    abstract fun bindBussinessInfoViewModel(businessInfoViewModel: InfoViewModel): ViewModel
}