package de.dannyb.imnuri.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.dannyb.imnuri.data.BuildConfig
import de.dannyb.imnuri.data.local.HymnDao
import de.dannyb.imnuri.data.remote.ImnuriService
import de.dannyb.imnuri.data.remote.dto.AppDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataAppModule {

    // Networking

    @Singleton
    @Provides
    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideImnuriService(retrofit: Retrofit): ImnuriService =
        retrofit.create(ImnuriService::class.java)

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    // Room Database

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "imnuri-db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideHymnDao(database: AppDatabase): HymnDao = database.hymnDao()

    // Android

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application

}