package com.example.acromine04032023.di

import com.example.acromine04032023.data.service.AcronymRepo
import com.example.acromine04032023.data.service.AcronymRepoImpl
import com.example.acromine04032023.data.service.AcronymService
import com.example.acromine04032023.usecase.MeaningUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesServiceAPI(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): AcronymService =
        Retrofit.Builder()
            .baseUrl(AcronymService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(AcronymService::class.java)

    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .connectTimeout(30,TimeUnit.SECONDS)
            .build()

    @Provides
    fun providesHttpLoggingInterceptor(
    ): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providesMoshi(): Moshi =
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(
        acronymRepoImpl: AcronymRepoImpl
    ): AcronymRepo
}

@Module
@InstallIn(SingletonComponent::class)
class ViewModelComponent {
    @Provides
    fun providesUseCase(repo: AcronymRepo): MeaningUseCase =
        MeaningUseCase(repo)

    @Provides
    fun providesIODistpacher(): CoroutineDispatcher = Dispatchers.IO
}