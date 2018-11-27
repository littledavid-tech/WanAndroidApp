package cn.shycoder.wanandroidapp.model

import cn.shycoder.wanandroidapp.SPKeyConst
import cn.shycoder.wanandroidapp.utils.MyApplication
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response


/**
 * 用于向OKHttp 请求头中填入Cookie 的拦截器
 * */
class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val builder = chain!!.request().newBuilder()
        //判断是否需要发送Cookie
        if (null != chain.request().headers().get(NetConst.SET_COOKIE)) {
            builder.removeHeader(NetConst.SET_COOKIE)
            val cookie = MyApplication.getStringFromSP(SPKeyConst.sp_key_cookie)
            Logger.e("Send cookie: Cookie=$cookie")
            builder.addHeader("Cookie", cookie)
        }
        return chain.proceed(builder.build())
    }
}