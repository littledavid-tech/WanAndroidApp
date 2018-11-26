package cn.shycoder.wanandroidapp.presenter.contract

import android.support.v7.view.menu.BaseMenuPresenter

/**
 * Created by ShyCoder on 11/26/2018.
 */
interface ArticleDetailContract {

    interface View : BaseContract.View {
        fun collectedArticle()
    }

    interface Presenter : BaseContract.Presenter<View> {
        /**
         * 收藏文章
         * */
        fun collectArticle()

        /**
         * 分享文章
         * */
        fun shareArticle()

        /**
         * 用系统浏览器打开文章
         * */
        fun openArticleInSstemBrowser(url: String)
    }

}