package cn.shycoder.wanandroidapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.View
import android.widget.TextView
import butterknife.BindString
import butterknife.BindView
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.entity.Hotkey
import cn.shycoder.wanandroidapp.presenter.SearchPresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.SearchContract
import cn.shycoder.wanandroidapp.view.BaseToolBarActivity
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout


class SearchActivity
    : BaseToolBarActivity<SearchContract.Presenter>(),
        SearchContract.View {

    @BindView(R.id.search_search_view)
    lateinit var searchView: MaterialSearchView

    @BindView(R.id.search_hot_key_tag_flow_layout)
    lateinit var tag_flow_layout: TagFlowLayout

    @BindString(R.string.search_activity_title)
    lateinit var activityTitle: String

    override fun getToolbarTitle(): String {
        return activityTitle
    }

    override fun getLayoutResId(): Int {
        return R.layout.search_activity
    }

    override fun doInit() {
        super.doInit()
        this.presenter?.loadHotkey()
        //为MaterialSearchView 设置点击事件
        this.searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter?.disposeSearchEvent(this@SearchActivity, query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun loadedHotkey(list: List<Hotkey>) {
        //加载搜索热词
        this.tag_flow_layout.adapter = object : TagAdapter<Hotkey>(list) {
            override fun getView(parent: FlowLayout?, position: Int, t: Hotkey?): View {
                val view = layoutInflater.inflate(R.layout.search_hot_key_tv_tag, parent, false)
                if (view is TextView) {
                    view.text = t!!.name
                }
                return view
            }
        }
        this.tag_flow_layout.setOnTagClickListener { view, position, parent ->
            val k = list[position].name
            this.presenter?.disposeSearchEvent(this@SearchActivity, k)
            true
        }
    }

    override fun createPresenter(): SearchContract.Presenter {
        return SearchPresenterImpl().apply { view = this@SearchActivity }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        /**
         * 初始化 MaterialSearchView
         * */
        this.menuInflater.inflate(R.menu.search_option_menu, menu)
        val menuItem = menu!!.findItem(R.id.search_menu)
        this.searchView.setMenuItem(menuItem)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        //先关闭SearchView
        if (this.searchView.isSearchOpen) {
            this.searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        fun show(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }
}