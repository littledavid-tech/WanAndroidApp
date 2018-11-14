package cn.shycoder.wanandroidapp.model.api

import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.model.entity.Paging
import cn.shycoder.wanandroidapp.model.entity.SuperEntity
import cn.shycoder.wanandroidapp.utils.RetrofitUtils
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface KnowledgeSystemArticleService {
    /**
     * 以分页的方式来获取指定知识库下的文章
     * */
    @GET("/article/tabList/{page}/json?cid={cid}")
    fun getArticles(@Path("page") pageIndex: Int, @Path("cid") cid: Int)
            : io.reactivex.Observable<SuperEntity<Paging<Article>>>

    companion object {
        val instance = RetrofitUtils.create<KnowledgeSystemArticleService>()
    }
}