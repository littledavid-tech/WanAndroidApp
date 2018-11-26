package cn.shycoder.wanandroidapp.presenter

import android.content.Context
import android.content.Intent
import android.net.Uri
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.api.UserService
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.model.entity.SuperEntity
import cn.shycoder.wanandroidapp.presenter.contract.ArticleDetailContract
import cn.shycoder.wanandroidapp.utils.CommonUtils
import cn.shycoder.wanandroidapp.utils.ToastUtils
import cn.shycoder.wanandroidapp.view.activity.ArticleDetailActivity
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ShyCoder on 11/26/2018.
 */
class ArticleDetailPresenterImpl(private val context: Context) : ArticleDetailContract.Presenter {

    private val mDomainName: String = "wanandroid.com"

    override var view: ArticleDetailContract.View? = null
    override var disposable: Disposable? = null

    private var mArticle: Article? = null

    override fun collectArticle(article: Article) {
        //如果包含指定的域名则确定是站内文章
        //否则则是站外文章
//        val observable: Observable<SuperEntity<Any>> = if (article.link!!.contains(mDomainName)) {
//            UserService
//                    .instance
//                    .collectInternalArticle(article.id)
//        } else {
//            UserService
//                    .instance
//                    .collectExternalArticle(article.title!!, article.author!!, article.link!!)
//        }
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
                    } else {
                        Logger.e(it.errorMsg)
                    }
                }, {
                    it.printStackTrace()
                })

    }

    override fun cancelCollectArticle(article: Article) {

    }

    override fun shareArticle(article: Article) {

    }

    override fun openArticleInSystemBrowser(article: Article) {
        val uri = Uri.parse(this.mArticle!!.link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (CommonUtils.isActivityExisted(this.context, intent)) {
            context.startActivity(intent)
        } else {
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
//        this.mArticle =
//                intent.getParcelableExtra(ArticleDetailActivity.INTENT_EXTRA_ARTICLE) as Article
        this.mArticle =
                intent.getParcelableExtra(ArticleDetailActivity.INTENT_EXTRA_ARTICLE) as Article
        Logger.i("ArticleId:${mArticle!!.id}")
        this.view!!.openArticle(mArticle!!.link!!)
        this.view!!.collectedArticle(mArticle!!.isCollect)
    }
}