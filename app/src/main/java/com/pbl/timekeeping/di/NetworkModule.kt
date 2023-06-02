package com.pbl.timekeeping.di

import com.pbl.timekeeping.common.Utils
import com.pbl.timekeeping.data.apis.AccountApi
import com.pbl.timekeeping.data.apis.DepartmentApi
import com.pbl.timekeeping.data.apis.EmployeeApi
import com.pbl.timekeeping.data.apis.EmployeeStatusApi
import com.pbl.timekeeping.data.apis.WorkScheduleApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }
    @Provides
    @Singleton
    fun providesRetrofit(moshiConverterFactory: MoshiConverterFactory ,
                         okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(Utils.BASE_URL)
            .build()
    }
    @Provides
    @Singleton
    fun provideOKHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.interceptors().add(httpLoggingInterceptor)
        return builder.build()
    }
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
    @Provides
    fun providesJsonPlaceHolderApi(retrofit: Retrofit) : DepartmentApi{
        return retrofit.create(DepartmentApi::class.java)
    }
    @Provides
    fun providesAccountApi(retrofit: Retrofit) : AccountApi{
        return retrofit.create(AccountApi::class.java)
    }
    @Provides
    fun providesEmployeeApi(retrofit : Retrofit) : EmployeeApi{
        return retrofit.create(EmployeeApi::class.java)
    }
    @Provides
    fun providesEmployeeStatusApi(retrofit: Retrofit) : EmployeeStatusApi{
        return retrofit.create(EmployeeStatusApi::class.java)
    }

    @Provides
    fun providesWorkScheduleApi(retrofit: Retrofit) : WorkScheduleApi{
        return retrofit.create(WorkScheduleApi::class.java)
    }
}