package cn.shycoder.wanandroidapp.view.activity

import android.content.Context
import android.content.Intent
import butterknife.BindString
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.adapter.recyclerview.MyCollectedArticleAdapter
import cn.shycoder.wanandroidapp.model.entity.CollectedArticle
import cn.shycoder.wanandroidapp.presenter.MyCollectedArticlePresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.MyCollectedArticleListContract
import cn.shycoder.wanandroidapp.utils.ToastUtils

/**
 * Created by ShyCoder on 11/28/2018.
 */
class MyCollectedArticleActivity
    : BaseRecyclerViewActivity<MyCollectedArticleListContract.Presenter>(),
        MyCollectedArticleListContract.View,
        MyCollectedArticleAdapter.OnRemoveCallBack {


    @BindString(R.string.my_collected_activity_title)
    lateinit var activityTitle: String

    private var mAdapter: MyCollectedArticleAdapter? = null

    override fun loadedData(list: List<CollectedArticle>) {
        if (null == mAdapter) {
            mAdapter = MyCollectedArticleAdapter(this, list.toMutableList())
            mAdapter?.onRemoveCallBack = this
            this.recyclerView.adapter = mAdapter
            return
        }
        mAdapter!!.addList(list)
        this.recyclerView.loadMoreComplete()
    }

    override fun refreshedData(list: List<CollectedArticle>) {
        this.mAdapter = null
        mAdapter = MyCollectedArticleAdapter(this, list.toMutableList())
        mAdapter?.onRemoveCallBack = this
        this.recyclerView.adapter = mAdapter
        this.recyclerView.refreshComplete()
    }

    /**
     * 移除收藏的文章
     * */
    override fun onRemove(position: Int, collectedArticle: CollectedArticle) {
        presenter?.removeCollectedArticle(collectedArticle.id, collectedArticle.originId, position)
    }

    override fun removedCollectedArticle(position: Int) {
        this.mAdapter?.removeAt(position)
    }

    override fun removeCollectedArticleFailed() {
        ToastUtils.show(R.string.my_collected_article_remove_failed)
    }

    override fun createPresenter(): MyCollectedArticleListContract.Presenter {
        return MyCollectedArticlePresenterImpl().apply { view = this@MyCollectedArticleActivity }
    }

    override fun getToolbarTitle(): String {
        return activityTitle
    }

    companion object {
        fun show(context: Context) {
            val intent = Intent(context, MyCollectedArticleActivity::class.java)
            context.startActivity(intent)
        }
    }
}