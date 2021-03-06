package cn.shycoder.wanandroidapp.presenter.contract

import android.content.Context
import android.content.Intent
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.presenter.base.BaseContract

/**
 * Created by ShyCoder on 11/26/2018.
 */
interface ArticleDetailContract {

    interface View : BaseContract.View {
        fun collectedArticle(isCollected: Boolean)

        fun openArticle(url: String)

        /**
         * 在收藏文章之前，提示用户登录
         * */
        fun pleaseLogin()
    }

    interface Presenter : BaseContract.Presenter<View> {
        /**
         * 收藏文章
         * */
        fun collectArticle(article: Article)

        /**
         * 取消收藏文章
         * */
        fun cancelCollectArticle(article: Article)

        /**
         * 分享文章
         * */
        fun shareArticle(article: Article)

        /**
         * 用系统浏览器打开文章
         * */
        fun openArticleInSystemBrowser(article: Article)

        /**
         * 处理选项菜单的点击事件
         * */
        fun disposeMenuEvent(context: Context, menuId: Int)

        /**
         * 加载文章
         * */
        fun loadArticle(intent: Intent)
    }

}