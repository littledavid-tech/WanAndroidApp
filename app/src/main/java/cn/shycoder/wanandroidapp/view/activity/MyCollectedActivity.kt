package cn.shycoder.wanandroidapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.PopupMenu
import butterknife.BindString
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.adapter.recyclerview.MyCollectedArticleAdapter
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.presenter.MyCollectedArticlePresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.MyCollectedArticleListContract

/**
 * Created by ShyCoder on 11/28/2018.
 */
class MyCollectedActivity
    : BaseRecyclerViewActivity<MyCollectedArticleListContract.Presenter>(),
        MyCollectedArticleListContract.View,
        MyCollectedArticleAdapter.OnDeleteListener {


    @BindString(R.string.my_collected_activity_title)
    lateinit var activity_title: String

    private var mAdapter: MyCollectedArticleAdapter? = null

    override fun loadedData(list: List<Article>) {
        if (null == mAdapter) {
            mAdapter = MyCollectedArticleAdapter(this, list.toMutableList())
            this.recyclerView.adapter = mAdapter
            return
        }
        mAdapter!!.addList(list)
    }

    override fun refreshedData(list: List<Article>) {
        this.mAdapter = null
        mAdapter = MyCollectedArticleAdapter(this, list.toMutableList())
        this.recyclerView.adapter = mAdapter
    }

    /**
     * 移除收藏的文章
     * */
    override fun onRemove(itemView: View, position: Int, article: Article) {
        val popupMenu = PopupMenu(this, itemView)
        menuInflater.inflate(R.menu.my_collected_item_popup_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            if (menuItem.itemId == R.id.my_collected_item_popup_menu_remove) {
                presenter?.removeCollectedArticle(article.id, position)
            }
            true
        }
        popupMenu.show()
    }

    override fun removedCollectedArticle(position: Int) {
        this.mAdapter?.removeAt(position)
    }

    override fun createPresenter(): MyCollectedArticleListContract.Presenter {
        return MyCollectedArticlePresenterImpl().apply { view = this@MyCollectedActivity }
    }

    override fun getToolbarTitle(): String {
        return activity_title
    }


    companion object {
        fun show(context: Context) {
            val intent = Intent(context, MyCollectedActivity::class.java)
            context.startActivity(intent)
        }
    }
}