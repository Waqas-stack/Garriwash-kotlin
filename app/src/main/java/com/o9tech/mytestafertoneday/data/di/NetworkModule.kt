package com.o9tech.mytestafertoneday.data.di

import android.content.Context
import com.o9tech.mytestafertoneday.data.SharedPrefrenceManager.SharedPrefrenceMa
import com.o9tech.mytestafertoneday.data.apiservices.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @GaariWashApi
    @Singleton
    @Provides
    fun providesRetrofitinstace(): Retrofit {
        return Retrofit.Builder().baseUrl("https://gaariwash.pk/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @GooglePlacesApi
    @Singleton
    @Provides
    fun provideGooglePlacesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    @GaariWashApi
    @Singleton
    @Provides
    fun providesApiServices(@GaariWashApi retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

    @GooglePlacesApi
    @Singleton
    @Provides
    fun provideGooglePlacesApiService(@GooglePlacesApi retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

    @Singleton
    @Provides
    fun provideSharedPrefManager(@ApplicationContext context: Context): SharedPrefrenceMa {
        return SharedPrefrenceMa(context)
    }

}