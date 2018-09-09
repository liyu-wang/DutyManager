package kevinsong.com.insighttimerdemo.module

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import kevinsong.com.data.businessinfo.BusinessInfoService
import kevinsong.com.data.shift.ShiftService
import kevinsong.com.dutymanager.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context) =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder()
                                .addHeader("Authorization", context.getString(R.string.scret_key))
                                .build())
                    }
                    .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, context: Context): Retrofit {
        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.baseurl))
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

    }

    @Provides
    fun provideShiftService(retrofit: Retrofit): ShiftService {
        return retrofit.create(ShiftService::class.java)
    }

    @Provides
    fun provideBussinessInfoService(retrofit: Retrofit): BusinessInfoService {
        return retrofit.create(BusinessInfoService::class.java)
    }
}