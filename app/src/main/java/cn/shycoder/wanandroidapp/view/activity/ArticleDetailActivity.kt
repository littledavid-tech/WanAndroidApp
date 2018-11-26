package cn.shycoder.wanandroidapp.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import butterknife.BindView
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.presenter.ArticleDetailPresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.ArticleDetailContract
import cn.shycoder.wanandroidapp.view.BaseToolBarActivity

/**
 * Created by ShyCoder on 11/26/2018.
 */
class ArticleDetailActivity
    : BaseToolBarActivity<ArticleDetailContract.Presenter>(), ArticleDetailContract.View {


    @BindView(R.id.article_detail_web_view)
    lateinit var webView: WebView

    private lateinit var mOptionMenu: Menu

    override fun getLayoutResId(): Int {
        return R.layout.article_detail_activity
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun doInit() {
        super.doInit()
        webView.settings.javaScriptEnabled = true
        presenter!!.loadArticle(this.intent)
    }

    /**
     *初始化选项菜单
     * */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_detail_option_menu, menu)
        this.mOptionMenu = menu!!
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        this.presenter?.disposeMenuEvent(this, item!!.itemId)
        return true
    }

    override fun collectedArticle(isCollected: Boolean) {
//        val collectMenu = this.mOptionMenu.findItem(R.id.article_detail_menu_collect)
//        collectMenu.setIcon(if (isCollected) R.drawable.app_like else R.drawable.app_unlike)
    }

    override fun openArticle(url: String) {
        webView.loadUrl(url)
    }

    override fun createPresenter(): ArticleDetailContract.Presenter {
        return ArticleDetailPresenterImpl().apply { view = this@ArticleDetailActivity }
    }

    override fun setToolbarTitle(title: String) {
        this.actionBar.title = title
    }

    override fun getToolbarTitle(): String {
        return "Article Detail"
    }

    companion object {
        val INTENT_EXTRA_ARTICLE = "article"

        fun show(context: Context, article: Article) {
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra(INTENT_EXTRA_ARTICLE, article)
            context.startActivity(intent)
        }
    }


}