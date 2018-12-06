package cn.shycoder.wanandroidapp.model.api

import cn.shycoder.wanandroidapp.model.NetConst
import cn.shycoder.wanandroidapp.model.entity.*
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
              @Field("password") password: String): Observable<SuperEntity<User>>

    /**
     * 获取收藏文章的列表
     * */
    @Headers(NetConst.COOKIE_HEADER)
    @GET("/lg/collect/list/{page}/json")
    fun getCollectedArticleList(@Path("page") pageIndex: Int): Observable<SuperEntity<Paging<CollectedArticle>>>

    /**
     * 收藏站内文章的API列表
     * */
    @Headers(NetConst.COOKIE_HEADER)
//    @FormUrlEncoded
    @POST("/lg/collect/{articleId}/json")
    fun collectInternalArticle(@Path("articleId") articleId: Int): Observable<SuperEntity<Any>>

    /**
     * 收藏站外文章
     * @param title 标题
     * @param author 作者
     * @param link 文章链接
     * */
    @Headers(NetConst.COOKIE_HEADER)
    @FormUrlEncoded
    @POST("/lg/collect/add/json")
    fun collectExternalArticle(@Field("activityTitle") title: String,
                               @Field("author") author: String,
                               @Field("link") link: String): Observable<SuperEntity<Any>>

    /**
     * 取消收藏
     * @param articleId
     * */
    @Headers(NetConst.COOKIE_HEADER)
    @POST("/lg/uncollect_originId/{articleId}/json")
    fun cancelCollect(@Path("articleId") articleId: Int): Observable<SuperEntity<Any>>

    /**
     * 在收藏列表界面取消收藏文章
     * @param collectedArticleId 收藏的文章的Id
     * @param originId 原始的文章的id
     * */
    @FormUrlEncoded
    @Headers(NetConst.COOKIE_HEADER)
    @POST("/lg/uncollect/{collectedArticleId}/json")
    fun cancelCollect(@Path("collectedArticleId") collectedArticleId: Int,
                      @Field("originId") originId: Int): Observable<SuperEntity<Any>>

    companion object {
        val instance = RetrofitUtils.create<UserService>()
    }
}