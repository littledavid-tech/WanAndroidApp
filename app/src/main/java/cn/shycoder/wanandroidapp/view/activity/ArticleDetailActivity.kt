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
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.presenter.ArticleDetailPresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.ArticleDetailContract
import cn.shycoder.wanandroidapp.utils.ToastUtils
import cn.shycoder.wanandroidapp.view.BaseToolBarActivity
import com.orhanobut.logger.Logger

/**
 * Created by ShyCoder on 11/26/2018.
 */
class ArticleDetailActivity
    : BaseToolBarActivity<ArticleDetailContract.Presenter>(), ArticleDetailContract.View {

    @BindView(R.id.article_detail_web_view)
    lateinit var webView: WebView

    @BindString(R.string.article_detail_title)
    lateinit var title: String

    private var mOptionMenu: Menu? = null

    private var mIsCollected = false//文章是否被收藏


    override fun getLayoutResId(): Int {
        return R.layout.article_detail_activity
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun doInit() {
        super.doInit()
        webView.settings.javaScriptEnabled = true
    }

    override fun onResume() {
        super.onResume()
        presenter!!.loadArticle(this.intent)
    }

    /**
     *初始化选项菜单
     * */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Logger.i("初始化菜单")
        menuInflater.inflate(R.menu.article_detail_option_menu, menu)
        this.mOptionMenu = menu!!
        collectedArticle(this.mIsCollected)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        this.presenter?.disposeMenuEvent(this, item!!.itemId)
        super.onOptionsItemSelected(item)
        return true
    }

    override fun pleaseLogin() {
        ToastUtils.show(R.string.app_msg_please_login)
    }

    override fun collectedArticle(isCollected: Boolean) {
        Logger.d("设置ICON")
        this.mIsCollected = isCollected
        if (this.mOptionMenu != null) {
            val collectMenu = this.mOptionMenu!!.findItem(R.id.article_detail_menu_collect)
            collectMenu.setIcon(if (isCollected) R.drawable.app_like else R.drawable.app_unlike)
        }
    }

    override fun openArticle(url: String) {
        webView.loadUrl(url)
    }

    override fun createPresenter(): ArticleDetailContract.Presenter {
        return ArticleDetailPresenterImpl(this).apply { view = this@ArticleDetailActivity }
    }

    override fun getToolbarTitle(): String {
        return this.title
    }

    companion object {
        const val INTENT_EXTRA_ARTICLE = "article"

        fun show(context: Context, article: Article) {
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra(INTENT_EXTRA_ARTICLE, article)
//            intent.putExtra(INTENT_EXTRA_ARTICLE, article)
            context.startActivity(intent)
        }

    }


}