package cn.shycoder.wanandroidapp.model.api

import cn.shycoder.wanandroidapp.model.entity.SuperEntity
import cn.shycoder.wanandroidapp.model.entity.UserInfo
import cn.shycoder.wanandroidapp.utils.RetrofitUtils
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 用户相关的API
 */
interface UserService {

    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("username") username: String,
              @Field("password") password: String): Observable<SuperEntity<UserInfo>>

    companion object {
        val instance = RetrofitUtils.create<UserService>()
    }
}