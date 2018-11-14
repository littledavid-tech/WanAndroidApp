package cn.shycoder.wanandroidapp.view.fragment

import cn.shycoder.wanandroidapp.adapter.recyclerview.KnowledgeSystemNodeAdapter
import cn.shycoder.wanandroidapp.model.entity.KnowledgeNode
import cn.shycoder.wanandroidapp.presenter.KnowledgeSystemPresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.KnowledgeSystemContract


class KnowledgeSystemFragment :
        BaseRecyclerViewFragment<KnowledgeSystemContract.Presenter>(),
        KnowledgeSystemContract.View {


    override fun doInit() {
        super.doInit()
        this.recyclerView.setLoadingMoreEnabled(false)
        this.presenter!!.loadMore()
    }

    override fun loadedKnowledge(list: List<KnowledgeNode>) {
        val adapter = KnowledgeSystemNodeAdapter(this.context, list.toMutableList())
        recyclerView.adapter = adapter
        recyclerView.refreshComplete()
    }


    override fun createPresenter(): KnowledgeSystemContract.Presenter {
        return KnowledgeSystemPresenterImpl().apply { view = this@KnowledgeSystemFragment }
    }
}