package cn.shycoder.wanandroidapp.presenter.contract

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import cn.shycoder.wanandroidapp.model.entity.CollectedArticle
import cn.shycoder.wanandroidapp.presenter.base.BaseContract

/**
 * Created by ShyCoder on 12/3/2018.
 */
interface CollectedArticleDetailContract {
    interface View : BaseContract.View {
        fun openCollectedArticle(collectedArticle: CollectedArticle)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun loadCollectedArticle(intent: Intent)

        fun shareCollectedArticle()

        fun openCollectedArticleInSystemBrowser()

        fun disposeOptionMenuEvent(context: Context, menuItem: MenuItem)
    }
}