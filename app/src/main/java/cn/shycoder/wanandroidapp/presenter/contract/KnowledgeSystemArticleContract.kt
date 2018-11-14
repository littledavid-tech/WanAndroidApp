package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.model.entity.Article

/**
 * Created by ShyCoder on 11/14/2018.
 */
interface KnowledgeSystemArticleContract {
    interface View : BaseRecyclerViewContract.View {

        fun loadedData(list: List<Article>)

        fun refreshedData(list: List<Article>)
    }

    interface Presenter : BaseRecyclerViewContract.Presenter<View> {

    }
}