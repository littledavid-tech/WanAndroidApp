package cn.shycoder.wanandroidapp.view.fragment

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Adapter
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.adapter.ArticleAdapter
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.presenter.ArticlePresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.ArticleContract

/**
 * Created by ITSoftware on 11/7/2018.
 */
class   ArticleFragment
    : BaseRecyclerViewFragment<ArticleContract.ArticlePresenter>(),
        ArticleContract.ArticleView {

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
            recyclerView.setAdapter(adapter)
        } else {
            adapter!!.addList(list)
        }
    }

    override fun refreshedData(list: List<Article>) {
        adapter = null
        adapter = ArticleAdapter(list.toMutableList())
        recyclerView.setAdapter(adapter)
    }

    override fun createPresenter(): ArticleContract.ArticlePresenter {
        return ArticlePresenterImpl(this)
    }
}