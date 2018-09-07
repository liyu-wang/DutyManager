package kevinsong.com.dutymanager.di

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kevinsong.com.dutymanager.shift.ShiftViewModel

@Module
abstract class ShiftViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShiftViewModel::class)
    abstract fun bindMyViewModel(myViewModel: ShiftViewModel): ViewModel
}