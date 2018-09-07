package kevinsong.com.insighttimerdemo.module

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import kevinsong.com.dutymanager.database.AppDatabase
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun providesRoomInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "duty_manager").build()
    }
}