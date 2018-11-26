package cn.shycoder.wanandroidapp.utils

import cn.shycoder.wanandroidapp.SPKeyConst
import cn.shycoder.wanandroidapp.model.CookieInterceptor
import cn.shycoder.wanandroidapp.model.NetConst
import cn.shycoder.wanandroidapp.model.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.logging.Logger

object RetrofitUtils {
    const val baseUrl = "http://www.wanandroid.com"

    inline fun <reified T> create(): T {
        val retrofit = Retrofit
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
                .build()
        return retrofit.create(T::class.java)
    }


//    val customClient by lazy {
//        val clientBuilder = OkHttpClient.Builder()
//        clientBuilder.addInterceptor { chain ->
//            val builder = chain!!.request().newBuilder()
//            //添加Cookie
//            if (chain.request().headers().get(NetConst.SET_COOKIE) != null) {
//                com.orhanobut.logger.Logger.e("添加Cookie")
//                builder.removeHeader(NetConst.SET_COOKIE)
//                val cookie = "loginUserName=" +
//                        "${MyApplication.getStringFromSP(SPKeyConst.sp_key_username)};" +
//                        "loginUserPassword=" +
//                        MyApplication.getStringFromSP(SPKeyConst.sp_key_password) + ";"
//
////                com.orhanobut.logger.Logger.e(cookie)
//                builder.addHeader("Cookie", cookie)
//            }
//            val request = builder.build()
//            chain.proceed(request)
//        }
//        clientBuilder.build()
//    }
}