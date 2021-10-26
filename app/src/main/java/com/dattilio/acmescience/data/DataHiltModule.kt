package com.dattilio.acmescience.data

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

@Module
@InstallIn(SingletonComponent::class) // Installs FooModule in the generate SingletonComponent.
internal object DataHiltModule {
    @Provides
    fun provideAcmeApi(retrofit: Retrofit): AcmeApi {
        return retrofit.create(AcmeApi::class.java)
    }

    @Provides
    fun provideRetrofit(moshiConverterFactory: MoshiConverterFactory): Retrofit {
        val logging =  HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        val client =  OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://endpoint.com")
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }
}