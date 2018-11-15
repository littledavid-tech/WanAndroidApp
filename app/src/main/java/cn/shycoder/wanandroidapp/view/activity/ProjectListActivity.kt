package cn.shycoder.wanandroidapp.view.activity

import android.content.Context
import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.widget.GridLayout
import cn.shycoder.wanandroidapp.adapter.recyclerview.ProjectAdapter
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.presenter.ProjectListPresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.ProjectListContract

/**
 * Created by ShyCoder on 11/15/2018.
 */
class ProjectListActivity
    : BaseRecyclerViewActivity<ProjectListContract.Presenter>(), ProjectListContract.View {

    private var mAdapter: ProjectAdapter? = null

    override fun setLayoutManager() {
        this.recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    override fun loadedData(list: List<Article>) {
        if (null == mAdapter) {
            mAdapter = ProjectAdapter(this, list.toMutableList())
            this.recyclerView.adapter = mAdapter
            return
        }
        mAdapter!!.addList(list)
    }

    override fun refreshedData(list: List<Article>) {
        this.mAdapter = null
        mAdapter = ProjectAdapter(this, list.toMutableList())
        this.recyclerView.adapter = mAdapter
    }

    override fun createPresenter(): ProjectListContract.Presenter {
        val cid = intent.getIntExtra(INTENT_EXTRA_PROJECT_CATEGORY_CID, -1)
        return ProjectListPresenterImpl(cid).apply { view = this@ProjectListActivity }
    }

    override fun getToolbarTitle(): String {
        return intent.getStringExtra(INTENT_EXTRA_PROJECT_CATEGORY_NAME)
    }

    companion object {

        const val INTENT_EXTRA_PROJECT_CATEGORY_NAME = "category_name"
        const val INTENT_EXTRA_PROJECT_CATEGORY_CID = "project_cid"

        /**
         * 启动 ProjectListActivity
         * @param context 上下文对象
         * @param projectCategoryName 项目种类的名称,这一个名称将作为Activity的标题
         * @param cid 项目种类的id，用来加载对应的项目的类型
         * */
        fun show(context: Context, projectCategoryName: String, cid: Int) {
            val intent = Intent(context, ProjectListActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PROJECT_CATEGORY_NAME, projectCategoryName)
            intent.putExtra(INTENT_EXTRA_PROJECT_CATEGORY_CID, cid)
            context.startActivity(intent)
        }
    }

}