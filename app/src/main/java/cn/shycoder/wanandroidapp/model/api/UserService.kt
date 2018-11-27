package cn.shycoder.wanandroidapp.model.api

import cn.shycoder.wanandroidapp.model.NetConst
import cn.shycoder.wanandroidapp.model.entity.SuperEntity
import cn.shycoder.wanandroidapp.model.entity.UserInfo
import cn.shycoder.wanandroidapp.utils.RetrofitUtils
import io.reactivex.Observable
import retrofit2.http.*

/**
 * 用户相关的API
 */
interface UserService {

    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("username") username: String,
              @Field("password") password: String): Observable<SuperEntity<UserInfo>>

    /**
     * 收藏站内文章的API列表
     * */
    @Headers(NetConst.SET_COOKIE + ":xxx")
//    @FormUrlEncoded
    @POST("/lg/collect/{articleId}/json")
    fun collectInternalArticle(@Path("articleId") articleId: Int): Observable<SuperEntity<Any>>

    /**
     * 收藏站外文章
     * @param title 标题
     * @param author 作者
     * @param link 文章链接
     * */
    @Headers(NetConst.SET_COOKIE + ":xxx")
    @FormUrlEncoded
    @POST("/lg/collect/add/json")
    fun collectExternalArticle(@Field("title") title: String,
                               @Field("author") author: String,
                               @Field("link") link: String): Observable<SuperEntity<Any>>

    /**
     * 取消收藏
     * @param articleId
     * */
    @Headers(NetConst.SET_COOKIE + ":xx")
    @POST("/lg/uncollect_originId/{articleId}/json")
    fun cancelCollect(@Path("articleId") articleId: Int): Observable<SuperEntity<Any>>

    companion object {
        val instance = RetrofitUtils.create<UserService>()
    }
}