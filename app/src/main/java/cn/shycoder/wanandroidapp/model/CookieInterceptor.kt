package cn.shycoder.wanandroidapp.model

import cn.shycoder.wanandroidapp.SPKeyConst
import cn.shycoder.wanandroidapp.utils.MyApplication
import okhttp3.Interceptor
import okhttp3.Response
import java.util.logging.Logger

/**
 * Created by ShyCoder on 11/26/2018.
 */
class CookieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val originResponse = chain!!.proceed(chain.request())
        //判断头部是否具有Cookie
        if (!originResponse.header("Set-Cookie").isNullOrBlank()) {
            originResponse.networkResponse()
            val cookie = originResponse.header("Set-Cookie")!!
            for (header in originResponse.headers("Set-Cookie")) {
                
            }
            com.orhanobut.logger.Logger.i("Cooke:${cookie}")
            if (cookie.contains("token_pass") and cookie.contains("loginUserName")) {
                MyApplication.putStringToSP(SPKeyConst.sp_key_cookie, cookie)
                com.orhanobut.logger.Logger.e("Save cookie")
            }
        }
        return originResponse
    }
}