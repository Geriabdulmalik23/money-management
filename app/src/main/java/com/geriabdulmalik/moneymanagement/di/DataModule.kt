package com.geriabdulmalik.moneymanagement.di

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.Collections
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val HOST_API = "http://10.0.2.2:8000/api/"

    @Provides
    fun provideRetrofitApi(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().serializeNulls().create()

                )
            )
            .client(okHttpClient)
            .baseUrl(HOST_API)
            .build()
    }

    @Provides
    fun provideOkHttpClientApi(
        @ApplicationContext appContext: Context,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequestBuilder = restfullHeader(chain = chain, context = appContext)
                chain.proceed(newRequestBuilder.build())
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(
                ChuckerInterceptor.Builder(appContext)
                    .collector(ChuckerCollector(appContext, showNotification = true))
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(true)
                    .build()
            )
            .apply {
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            .build()
    }

    private fun restfullHeader(chain: Interceptor.Chain, context: Context): Request.Builder {
        val manager = context.packageManager
        var versionName = ""
        var versionCode = ""
        try {
            val info = manager.getPackageInfo(context.packageName, 0)
            versionName = info.versionName

            versionCode = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                info.versionCode.toString()
            } else {
                info.longVersionCode.toString()
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            versionName = "Unknown"
        }

        return chain
            .request()
            .newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader(
                "Authorization",
                "Bearer 13|X76pQG9WTJtWlWjJvJyR0epF3OKd31Fnz4OlGLeI3b70a94f"
            )
            .addHeader("X-MM-Platform", "android")
            .addHeader("X-MM-App", "customer")
            .addHeader("X-MM-Version", versionName)
            .addHeader("X-MM-Build", versionCode)
    }
}