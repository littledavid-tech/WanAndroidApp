package cn.shycoder.wanandroidapp.view.fragment

import android.annotation.SuppressLint
import cn.shycoder.wanandroidapp.adapter.recyclerview.KnowledgeSystemArticleAdapter
import cn.shycoder.wanandroidapp.model.bean.KnowledgeSystemTab
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.presenter.KnowledgeSystemArticlePresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.KnowledgeSystemArticleContract
import com.orhanobut.logger.Logger

@SuppressLint("ValidFragment")
class KnowledgeSystemArticleFragment(val tab: KnowledgeSystemTab)
    : BaseRecyclerViewFragment<KnowledgeSystemArticleContract.Presenter>()
        , KnowledgeSystemArticleContract.View {

    var adapter: KnowledgeSystemArticleAdapter? = null

    override fun onDestroy() {
        super.onDestroy()
        Logger.i("Knowledge System Article Fragment onDestroy")
    }

    override fun doInit() {
        super.doInit()
        this.presenter!!.loadMore()
    }

    override fun loadedData(list: List<Article>) {
        if (adapter == null) {
            adapter = KnowledgeSystemArticleAdapter(this.context, list.toMutableList())
            recyclerView.adapter = adapter
            recyclerView.loadMoreComplete()
        } else {
            adapter!!.addList(list)
            recyclerView.loadMoreComplete()
        }
    }

    override fun refreshedData(list: List<Article>) {
        adapter = null
        adapter = KnowledgeSystemArticleAdapter(this.context, list.toMutableList())
        recyclerView.adapter = adapter
        recyclerView.refreshComplete()
    }

    override fun createPresenter(): KnowledgeSystemArticleContract.Presenter {
        return KnowledgeSystemArticlePresenterImpl(this.tab.cId)
                .apply { view = this@KnowledgeSystemArticleFragment }
    }


}