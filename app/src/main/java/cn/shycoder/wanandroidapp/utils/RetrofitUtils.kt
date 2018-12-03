package cn.shycoder.wanandroidapp.utils

import cn.shycoder.wanandroidapp.model.CookieInterceptor
import cn.shycoder.wanandroidapp.model.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitUtils {
    private const val baseUrl = "http://www.wanandroid.com"
    val instance = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient
                    .Builder()
                    .addInterceptor(RequestInterceptor())
                    .addInterceptor(CookieInterceptor())
                    .build())
            .build()!!

    inline fun <reified T> create(): T {
        return instance.create(T::class.java)
    }
}