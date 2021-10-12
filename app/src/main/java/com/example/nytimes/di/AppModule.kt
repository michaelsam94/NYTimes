package com.example.nytimes.di

import com.example.nytimes.common.Constants
import com.example.nytimes.data.remote.NYApi
import com.example.nytimes.data.repository.NyRepositoryImp
import com.example.nytimes.domain.repository.NyRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNYApi(): NYApi {
        val httpLogInterceptor = HttpLoggingInterceptor()
        httpLogInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().apply {
                addInterceptor(Interceptor { chain ->
                    val original = chain.request()
                    val originalHttpUrl = original.url
                    val url =
                        originalHttpUrl.newBuilder().addQueryParameter("api-key", Constants.API_KEY)
                            .build()
                    val builder = chain.request().newBuilder().url(url)
                    builder.header("App-ID", "c5555ca5-304f-4f7a-918f-bc6963b55c5e")
                    builder.header("Password", "51Kjxaeudnnadb060520190ADMIN3360520190345@ucs.ae")
                    return@Interceptor chain.proceed(builder.build())
                })
                addInterceptor(httpLogInterceptor)
            }.build())
            .build().create(NYApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNyRepository(api: NYApi): NyRepository {
        return NyRepositoryImp(api)
    }


}