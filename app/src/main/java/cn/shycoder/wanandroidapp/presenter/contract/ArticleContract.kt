package cn.shycoder.wanandroidapp.presenter.contract

/**
 * Created by ITSoftware on 11/7/2018.
 */
interface ArticleContract {
    interface ArticleView : BaseRecyclerViewContract.BaseRecyclerView {

    }

    interface ArticlePresenter : BaseRecyclerViewContract.BaseRecyclerPresenter<ArticleView> {

    }
}