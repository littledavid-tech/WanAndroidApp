package cn.shycoder.wanandroidapp.model.api

import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.model.entity.Paging
import cn.shycoder.wanandroidapp.model.entity.SuperEntity
import cn.shycoder.wanandroidapp.utils.RetrofitUtils
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KnowledgeSystemArticleService {
    /**
     * 以分页的方式来获取指定知识库下的文章
     * */
    @GET("/article/list/{page}/json")
    fun getArticles(@Path("page") pageIndex: Int, @Query("cid") cid: Int)
            : io.reactivex.Observable<SuperEntity<Paging<Article>>>

    companion object {
        val instance = RetrofitUtils.create<KnowledgeSystemArticleService>()
    }
}