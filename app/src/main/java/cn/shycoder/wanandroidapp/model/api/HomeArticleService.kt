package cn.shycoder.wanandroidapp.model.api

import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.model.entity.Paging
import cn.shycoder.wanandroidapp.model.entity.SuperEntity
import cn.shycoder.wanandroidapp.utils.RetrofitUtils
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 请求首页文章的Api接口
 * */
interface HomeArticleService {
    /**
     * 以分页查询的方式获取首页的Articles
     * */
    @GET("/article/list/{page}/json")
    fun getArticles(@Path("page") pageIndex: Int): io.reactivex.Observable<SuperEntity<Paging<Article>>>

    companion object {
        var instance: HomeArticleService = RetrofitUtils.create()
    }
}