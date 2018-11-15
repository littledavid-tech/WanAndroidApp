package cn.shycoder.wanandroidapp.view.fragment

import android.annotation.SuppressLint
import android.support.v7.widget.GridLayoutManager
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.presenter.ProjectListPresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.ProjectListContract

@SuppressLint("ValidFragment")
/**
 * Created by ShyCoder on 11/15/2018.
 */
class ProjectListFragment(val cid: Int)
    : BaseRecyclerViewFragment<ProjectListContract.Presenter>(),
        ProjectListContract.View {

    override fun setLayoutManager() {
        this.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
    }

    override fun loadedData(list: List<Article>) {

    }

    override fun refreshedData(list: List<Article>) {
    }

    override fun createPresenter(): ProjectListContract.Presenter {
        return ProjectListPresenterImpl(this.cid).apply { view = this@ProjectListFragment }
    }
}