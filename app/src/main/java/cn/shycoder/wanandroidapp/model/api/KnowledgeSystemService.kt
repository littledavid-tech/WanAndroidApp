package cn.shycoder.wanandroidapp.model.api

import cn.shycoder.wanandroidapp.model.entity.KnowledgeNode
import cn.shycoder.wanandroidapp.model.entity.SuperEntity
import cn.shycoder.wanandroidapp.utils.RetrofitUtils
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by ITSoftware on 11/8/2018.
 */
interface KnowledgeSystemService {
    /**
     * 获取知识体系的所有的节点
     * */
    @GET("/tree/json")
    fun getAllKnowledgeNode(): Observable<SuperEntity<List<KnowledgeNode>>>

    companion object {
        val instance = RetrofitUtils.create<KnowledgeSystemService>()
    }
}