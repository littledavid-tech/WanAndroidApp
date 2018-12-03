package cn.shycoder.wanandroidapp.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import butterknife.BindString
import butterknife.BindView
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.entity.CollectedArticle
import cn.shycoder.wanandroidapp.presenter.CollectedArticleDetailPresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.CollectedArticleDetailContract
import cn.shycoder.wanandroidapp.view.BaseToolBarActivity

class CollectedArticleDetailActivity
    : BaseToolBarActivity<CollectedArticleDetailContract.Presenter>(),
        CollectedArticleDetailContract.View {

    @BindView(R.id.collected_article_detail_web_view)
    lateinit var webView: WebView

    @BindString(R.string.article_detail_title)
    lateinit var activityTitle: String

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.collected_detail_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)
        this.presenter?.disposeOptionMenuEvent(this, item!!)
        return true
    }

    override fun getLayoutResId(): Int {
        return R.layout.collected_article_detail_activity
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun doInit() {
        super.doInit()
        this.webView.settings.javaScriptEnabled = true
        this.presenter?.loadCollectedArticle(this.intent)
    }

    override fun openCollectedArticle(collectedArticle: CollectedArticle) {
        webView.loadUrl(collectedArticle.link)
    }

    override fun createPresenter(): CollectedArticleDetailContract.Presenter {
        return CollectedArticleDetailPresenterImpl(this).apply {
            view = this@CollectedArticleDetailActivity
        }
    }

    override fun getToolbarTitle(): String {
        return activityTitle
    }

    companion object {

        const val INTENT_EXTRA_COLLCTED_ARTICLE = "collected_article"

        fun show(context: Context, collectedArticle: CollectedArticle) {
            val intent = Intent(context, CollectedArticleDetailActivity::class.java)
            intent.putExtra(INTENT_EXTRA_COLLCTED_ARTICLE, collectedArticle)
            context.startActivity(intent)
        }
    }
}