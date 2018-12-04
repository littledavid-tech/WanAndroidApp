package cn.shycoder.wanandroidapp.model.api

import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.model.entity.Hotkey
import cn.shycoder.wanandroidapp.model.entity.Paging
import cn.shycoder.wanandroidapp.model.entity.SuperEntity
import cn.shycoder.wanandroidapp.utils.RetrofitUtils
import io.reactivex.Observable
import retrofit2.http.*

/**
 * 搜索服务相关的接口
 * */
interface SearchService {
    /**
     * 获取搜索热词
     * */
    @GET("/hotkey/json")
    fun getHotKey(): Observable<SuperEntity<List<Hotkey>>>

    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    fun searchArticle(@Path("page") pageIndex: Int,
                      @Field("k") keyWords: String): io.reactivex.Observable<SuperEntity<Paging<Article>>>


    companion object {
        var instance: SearchService = RetrofitUtils.create()
    }
}