package cn.shycoder.wanandroidapp.view.activity

import android.content.Context
import android.content.Intent
import butterknife.BindString
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.adapter.recyclerview.ArticleAdapter
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.presenter.SearchResultPresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.SearchResultContract
import cn.shycoder.wanandroidapp.view.BaseToolBarActivity

/**
 * Created by ShyCoder on 12/4/2018.
 */
class SearchResultActivity
    : BaseRecyclerViewActivity<SearchResultContract.Presenter>(),
        SearchResultContract.View {

    private var adapter: ArticleAdapter? = null

    @BindString(R.string.search_result_activity_title)
    lateinit var activityTitle: String

    override fun doInit() {
        super.doInit()
        presenter?.loadMore()
    }

    override fun loadedData(list: List<Article>) {
        if (adapter == null) {
            adapter = ArticleAdapter(this, list.toMutableList())
            recyclerView.adapter = adapter
            recyclerView.loadMoreComplete()
        } else {
            adapter!!.addList(list)
            recyclerView.loadMoreComplete()
        }
    }

    override fun refreshedData(list: List<Article>) {
        adapter = null
        adapter = ArticleAdapter(this, list.toMutableList())
        recyclerView.adapter = adapter
        recyclerView.refreshComplete()
    }

    override fun createPresenter(): SearchResultContract.Presenter {
        val key = intent.getStringExtra(INTENT_EXTRA_KEY)
        return SearchResultPresenterImpl(key).apply { view = this@SearchResultActivity }
    }

    override fun getToolbarTitle(): String {
        return activityTitle
    }

    companion object {
        const val INTENT_EXTRA_KEY = "key"

        fun show(content: Context, key: String) {
            val intent = Intent(content, SearchResultActivity::class.java)
            intent.putExtra(INTENT_EXTRA_KEY, key)
            content.startActivity(intent)
        }
    }
}