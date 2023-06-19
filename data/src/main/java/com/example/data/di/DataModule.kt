package com.example.data.di

import com.example.data.FreeSoundApiService
import com.example.data.MyTokenInterceptor
import com.example.data.SongsRepoImpl
import com.example.domain.SongsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object DataModule {

    @Provides

    fun provideInterceptor(): MyTokenInterceptor{
        val myToken = "U5MVXqy7LT31EfmZJTMaUayY58TL1M8b8SvmBaeA"
        return MyTokenInterceptor(token = myToken)
    }

    @Provides
    @Singleton
    fun provideHttpClient(tokenInterceptor: MyTokenInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(interceptor = tokenInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://freesound.org")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMyApiService(retrofit: Retrofit): FreeSoundApiService = retrofit.create(FreeSoundApiService::class.java)

    @Provides
    @Singleton
    fun provideSoundRepo(apiService: FreeSoundApiService):SongsRepo{
        return SongsRepoImpl(apiService)
    }

}