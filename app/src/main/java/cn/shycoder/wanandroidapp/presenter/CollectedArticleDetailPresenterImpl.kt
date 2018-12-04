package cn.shycoder.wanandroidapp.presenter

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.entity.CollectedArticle
import cn.shycoder.wanandroidapp.presenter.contract.CollectedArticleDetailContract
import cn.shycoder.wanandroidapp.utils.CommonUtils
import cn.shycoder.wanandroidapp.utils.ToastUtils
import cn.shycoder.wanandroidapp.view.activity.CollectedArticleDetailActivity
import io.reactivex.disposables.Disposable


class CollectedArticleDetailPresenterImpl(val context: Context)
    : BasePresenter<CollectedArticleDetailContract.View>(), CollectedArticleDetailContract.Presenter {

    private var mCollectedArticle: CollectedArticle? = null

    override fun loadCollectedArticle(intent: Intent) {
        this.mCollectedArticle = intent.getParcelableExtra<CollectedArticle>(CollectedArticleDetailActivity
                .INTENT_EXTRA_COLLECTED_ARTICLE)
        this.view?.openCollectedArticle(mCollectedArticle!!)
    }

    override fun shareCollectedArticle() {
        ToastUtils.show(R.string.article_detail_msg_share_function_will_be_added_soon)
    }

    override fun openCollectedArticleInSystemBrowser() {
        CommonUtils.openWebSiteInBrowser(context, this.mCollectedArticle!!.link!!)
    }

    override fun disposeOptionMenuEvent(context: Context, menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.collected_article_detail_menu_open_in_system_browser -> {
                this.openCollectedArticleInSystemBrowser()
            }
            R.id.collected_article_detail_menu_share -> {
                this.shareCollectedArticle()
            }
            else -> {

            }
        }
    }


}