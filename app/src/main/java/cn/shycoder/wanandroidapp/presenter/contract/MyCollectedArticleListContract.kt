package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.model.entity.Article

/**
 * Created by ShyCoder on 11/27/2018.
 */
interface MyCollectedArticleListContract {
    interface View : BaseRecyclerViewContract.View {
        fun loadedData(list: List<Article>)

        fun refreshedData(list: List<Article>)

        fun removedCollectedArticle(position: Int)
    }

    interface Presenter : BaseRecyclerViewContract.Presenter<View> {
        /**
         * 添加站外链接
         * */
        fun addExternalLink()

        /**
         * 移除收藏的文章
         * @param articleId 文章的Id
         * */
        fun removeCollectedArticle(articleId: Int, position: Int)
    }
}