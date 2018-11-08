package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.model.entity.KnowledgeNode

/**
 * Created by ITSoftware on 11/8/2018.
 */
interface KnowledgeSystemContract {

    interface View : BaseRecyclerViewContract.View {
        fun loadedKnowledge(list: List<KnowledgeNode>)
    }

    interface Presenter : BaseRecyclerViewContract.Presenter<View> {
    }
}