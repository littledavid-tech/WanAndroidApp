package cn.shycoder.wanandroidapp.utils

import cn.shycoder.wanandroidapp.SPKeyConst
import cn.shycoder.wanandroidapp.model.NetConst
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitUtils {
    const val baseUrl = "http://www.wanandroid.com"

    inline fun <reified T> create(): T {
        val retrofit = Retrofit
                .Builder()
                .client(customClient)
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(T::class.java)
    }


    val customClient by lazy {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor { chain ->
            val builder = chain!!.request().newBuilder()
            //添加Cookie
            if (chain.request().headers().get(NetConst.SET_COOKIE) != null) {
                builder.removeHeader(NetConst.SET_COOKIE)
                builder.addHeader("Set-Cookie",
                        "loginUserName=" +
                                "${MyApplication.getStringFromSP(SPKeyConst.sp_key_username)};" +
                                "loginUserPassword=" +
                                MyApplication.getStringFromSP(SPKeyConst.sp_key_password))
            }
            val request = builder.build()
            chain.proceed(request)
        }
        clientBuilder.build()
    }
}