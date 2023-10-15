package de.dannyb.imnuri.data.di

import dagger.Module
import dagger.Provides
import de.dannyb.imnuri.data.remote.ImnuriService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import de.dannyb.imnuri.data.BuildConfig

@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

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
}