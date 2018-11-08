package cn.shycoder.wanandroidapp.view.fragment

import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.adapter.ArticleAdapter
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.presenter.ArticlePresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.ArticleContract

/**
 * 显示首页文章的碎片
 */
class ArticleFragment
    : BaseRecyclerViewFragment<ArticleContract.Presenter>(),
        ArticleContract.View {

    var adapter: ArticleAdapter? = null

    override fun doInit() {
        super.doInit()
        presenter?.loadMore()
    }

    override fun getLayoutResId(): Int {
        return R.layout.recycler_view_fragment
    }

    override fun loadedData(list: List<Article>) {
        if (adapter == null) {
            adapter = ArticleAdapter(list.toMutableList())
            recyclerView.adapter = adapter
            recyclerView.loadMoreComplete()
        } else {
            adapter!!.addList(list)
            recyclerView.loadMoreComplete()
        }
    }

    override fun refreshedData(list: List<Article>) {
        adapter = null
        adapter = ArticleAdapter(list.toMutableList())
        recyclerView.adapter = adapter
        recyclerView.refreshComplete()
    }

    override fun createPresenter(): ArticleContract.Presenter {
        return ArticlePresenterImpl().apply { view = this@ArticleFragment }
    }
}