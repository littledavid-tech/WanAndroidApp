package cn.shycoder.wanandroidapp.model.api

import cn.shycoder.wanandroidapp.model.NetConst
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.model.entity.HomeBanner
import cn.shycoder.wanandroidapp.model.entity.Paging
import cn.shycoder.wanandroidapp.model.entity.SuperEntity
import cn.shycoder.wanandroidapp.utils.RetrofitUtils
import retrofit2.http.*

/**
 * 请求首页文章的Api接口
 * */
interface HomeArticleService {
    /**
     * 以分页查询的方式获取首页的Articles
     * */
    @GET("/article/list/{page}/json")
    fun getArticles(@Path("page") pageIndex: Int): io.reactivex.Observable<SuperEntity<Paging<Article>>>

    /**
     * 获取首页的Banner
     * */
    @GET("/banner/json")
    fun getBanners(): io.reactivex.Observable<SuperEntity<List<HomeBanner>>>

    companion object {
        /**
         * HomeArticleService的实例
         * */
        var instance: HomeArticleService = RetrofitUtils.create()
    }
}