package cn.shycoder.wanandroidapp.model

import cn.shycoder.wanandroidapp.SPKeyConst
import cn.shycoder.wanandroidapp.MyApplication
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 用于截获响应的cookie的OKHttp的拦截器
 * */
class CookieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val originResponse = chain!!.proceed(chain.request())
        //判断头部是否具有Cookie
        if (!originResponse.header("Set-Cookie").isNullOrBlank()) {

            //读取所有的cookie
            val sb = StringBuffer()
            for (header in originResponse.headers("Set-Cookie")) {
                try {
                    val cookie = header.split(";")[0]
                    sb.append("$cookie;")
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
            //持久化Cookie
            if (sb.isNotEmpty()) {
                //判断是否是登录返回的cookie
                //TODO 这是一个相当脆弱的判断
                if (sb.contains("token_pass")) {
                    com.orhanobut.logger.Logger.e("Save Cookie:$sb")
                    MyApplication.putStringToSP(SPKeyConst.sp_key_cookie, sb.toString())
                }
            }
        }
        return originResponse
    }
}