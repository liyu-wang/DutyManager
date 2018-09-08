package kevinsong.com.dutymanager

import android.app.Application
import com.facebook.stetho.Stetho
import kevinsong.com.insighttimerdemo.module.AppComponent
import kevinsong.com.insighttimerdemo.module.AppModule
import kevinsong.com.insighttimerdemo.module.DaggerAppComponent

class DutyManagerApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initDagger(app: DutyManagerApplication): AppComponent {
        return DaggerAppComponent.builder().appModule(AppModule(app))
                .build()
    }
}