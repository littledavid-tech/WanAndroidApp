package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.model.entity.CollectedArticle

/**
 * Created by ShyCoder on 11/27/2018.
 */
interface MyCollectedArticleListContract {
    interface View : BaseRecyclerViewContract.View {
        fun loadedData(list: List<CollectedArticle>)

        fun refreshedData(list: List<CollectedArticle>)

        fun removedCollectedArticle(position: Int)

        fun removeCollectedArticleFailed()
    }

    interface Presenter : BaseRecyclerViewContract.Presenter<View> {
        /**
         * 添加站外链接
         * */
        fun addExternalLink()

        /**
         * 移除收藏的文章
         * @param collectedArticleId 收藏的文章的id
         * @param originId 原始文章的id
         * @param position 在recyclerview中的位置，为了通知recyclerview刷新指定的位置的数据
         * */
        fun removeCollectedArticle(collectedArticleId: Int, originId: Int, position: Int)
    }
}