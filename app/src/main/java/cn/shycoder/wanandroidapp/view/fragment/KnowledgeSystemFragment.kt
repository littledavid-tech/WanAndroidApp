package cn.shycoder.wanandroidapp.view.fragment

import cn.shycoder.wanandroidapp.adapter.KnowledgeSystemNodeAdapter
import cn.shycoder.wanandroidapp.model.entity.KnowledgeNode
import cn.shycoder.wanandroidapp.presenter.KnowledgeSystemPresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.KnowledgeSystemContract
import cn.shycoder.wanandroidapp.view.BaseFragment

/**
 * Created by ITSoftware on 11/8/2018.
 */
class KnowledgeSystemFragment :
        BaseRecyclerViewFragment<KnowledgeSystemContract.Presenter>(),
        KnowledgeSystemContract.View {


    override fun doInit() {
        super.doInit()
        this.recyclerView.setLoadingMoreEnabled(false)
        this.presenter!!.loadMore()
    }

    override fun loadedKnowledge(list: List<KnowledgeNode>) {
        val adapter = KnowledgeSystemNodeAdapter(list.toMutableList())
        recyclerView.adapter = adapter
        recyclerView.refreshComplete()
    }


    override fun createPresenter(): KnowledgeSystemContract.Presenter {
        return KnowledgeSystemPresenterImpl().apply { view = this@KnowledgeSystemFragment }
    }
}