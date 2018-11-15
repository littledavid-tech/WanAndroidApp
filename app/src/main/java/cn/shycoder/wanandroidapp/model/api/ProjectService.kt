package cn.shycoder.wanandroidapp.model.api

import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.model.entity.Paging
import cn.shycoder.wanandroidapp.model.entity.ProjectCategory
import cn.shycoder.wanandroidapp.model.entity.SuperEntity
import cn.shycoder.wanandroidapp.utils.RetrofitUtils
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 项目相关的API接口
 */
interface ProjectService {
    /**
     * 获取所有的项目的分类
     * */
    @GET("/project/tree/json")
    fun getProjectCategory(): Observable<SuperEntity<List<ProjectCategory>>>

    /**
     * 以分页的方式获取指定种类项目的列表
     * 注意: 这里的页数 1 是第一页
     * */
    @GET("/project/list/{page}/json")
    fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): Observable<SuperEntity<Paging<Article>>>

    companion object {
        var instance = RetrofitUtils.create<ProjectService>()
    }
}