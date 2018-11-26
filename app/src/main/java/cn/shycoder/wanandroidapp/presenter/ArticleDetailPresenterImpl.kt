package cn.shycoder.wanandroidapp.presenter

import android.content.Context
import android.content.Intent
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.presenter.contract.ArticleDetailContract
import cn.shycoder.wanandroidapp.view.activity.ArticleDetailActivity
import io.reactivex.disposables.Disposable

/**
 * Created by ShyCoder on 11/26/2018.
 */
class ArticleDetailPresenterImpl() : ArticleDetailContract.Presenter {

    override var view: ArticleDetailContract.View? = null
    override var disposable: Disposable? = null

    private var mArticle: Article? = null

    override fun collectArticle(article: Article) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun shareArticle(article: Article) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openArticleInSystemBrowser(article: Article) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun disposeMenuEvent(context: Context, menuId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadArticle(intent: Intent) {
        this.mArticle =
                intent.getParcelableExtra(ArticleDetailActivity.INTENT_EXTRA_ARTICLE) as Article
        this.view!!.openArticle(mArticle!!.link!!)
        this.view!!.collectedArticle(mArticle!!.isCollect)
    }
}