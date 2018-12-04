package cn.shycoder.wanandroidapp.presenter

import android.content.Context
import android.content.Intent
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.api.UserService
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.presenter.base.BasePresenter
import cn.shycoder.wanandroidapp.presenter.contract.ArticleDetailContract
import cn.shycoder.wanandroidapp.utils.CommonUtils
import cn.shycoder.wanandroidapp.MyApplication
import cn.shycoder.wanandroidapp.utils.ToastUtils
import cn.shycoder.wanandroidapp.view.activity.ArticleDetailActivity
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ShyCoder on 11/26/2018.
 */
class ArticleDetailPresenterImpl(private val context: Context)
    : BasePresenter<ArticleDetailContract.View>(), ArticleDetailContract.Presenter {
    private var mArticle: Article? = null

    override fun collectArticle(article: Article) {
        if (null == MyApplication.currentUser) {
            view?.pleaseLogin()
            return
        }

        UserService
                .instance
                .collectInternalArticle(article.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.errorCode == 0) {
                        Logger.i("Collect article")
                        view?.collectedArticle(true)
                        this.mArticle!!.isCollect = true
                        MyApplication.currentUser?.addCollectedArticle(article.id)
                    } else {
                        Logger.e(it.errorMsg)
                    }
                }, {
                    this.disposeException(it)
                    it.printStackTrace()
                }, {

                }, {
                    this.addDisposable(it)
                })

    }

    override fun cancelCollectArticle(article: Article) {
        UserService
                .instance
                .cancelCollect(article.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.errorCode == 0) {
                        view?.collectedArticle(false)
                        this.mArticle!!.isCollect = false
                        MyApplication.currentUser?.removeColletedArticle(article.id)
                    } else {
                        Logger.e(it.errorMsg)
                    }
                }, {
                    this.disposeException(it)
                    it.printStackTrace()
                }, {

                }, {
                    this.addDisposable(it)
                })
    }

    override fun shareArticle(article: Article) {
    }

    override fun openArticleInSystemBrowser(article: Article) {
        if (!CommonUtils.openWebSiteInBrowser(context, article.link!!)) {
            Logger.e("Cannot find the system browser!")
        }
    }

    override fun disposeMenuEvent(context: Context, menuId: Int) {
        when (menuId) {
            R.id.article_detail_menu_open_in_system_browser -> {//以系统浏览器打开网页
                this.openArticleInSystemBrowser(this.mArticle!!)
            }
            R.id.article_detail_menu_share -> {//分享功能
                shareArticle(this.mArticle!!)
                ToastUtils.show(R.string.article_detail_msg_share_function_will_be_added_soon)
            }
            R.id.article_detail_menu_collect -> {//收藏文章
                if (this.mArticle!!.isCollect) {
                    cancelCollectArticle(this.mArticle!!)
                } else {
                    collectArticle(this.mArticle!!)
                }
            }
            else -> {
            }
        }
    }

    override fun loadArticle(intent: Intent) {
        this.mArticle =
                intent.getParcelableExtra(ArticleDetailActivity.INTENT_EXTRA_ARTICLE) as Article
        Logger.i("ArticleId:${mArticle!!.id}")
        this.view!!.openArticle(mArticle!!.link!!)
        val isCollected = MyApplication.currentUser != null &&
                MyApplication.currentUser!!.isArticleCollected(mArticle!!.id)
        Logger.i("ID:${mArticle!!.id}")
        Logger.i(isCollected.toString())
        this.view!!.collectedArticle(isCollected)
    }
}