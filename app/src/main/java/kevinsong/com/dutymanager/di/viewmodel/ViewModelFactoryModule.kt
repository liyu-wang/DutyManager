package kevinsong.com.dutymanager.di.viewmodel

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}