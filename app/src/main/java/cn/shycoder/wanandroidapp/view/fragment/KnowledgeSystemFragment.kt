package cn.shycoder.wanandroidapp.view.fragment

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

//    private  var adapter

    override fun doInit() {
        super.doInit()
        this.recyclerView.setLoadingMoreEnabled(false)
        this.presenter!!.loadMore()
    }

    override fun loadedKnowledge(list: List<KnowledgeNode>) {

    }


    override fun createPresenter(): KnowledgeSystemContract.Presenter {
        return KnowledgeSystemPresenterImpl()
    }
}