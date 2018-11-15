package cn.shycoder.wanandroidapp.view.fragment

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import butterknife.BindView
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.adapter.recyclerview.ProjectCategoryAdapter
import cn.shycoder.wanandroidapp.model.bean.ProjectTab
import cn.shycoder.wanandroidapp.presenter.ProjectPresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.ProjectContract
import cn.shycoder.wanandroidapp.view.BaseFragment
import com.orhanobut.logger.Logger

class ProjectFragment
    : BaseRecyclerViewFragment<ProjectContract.Presenter>(),
        ProjectContract.View {


    override fun doInit() {
        super.doInit()
        this.enableLoadMore(false)
        this.presenter!!.loadMore()
    }

    override fun loadedProjectCategory(projectTabList: List<ProjectTab>) {
        val adapter = ProjectCategoryAdapter(this.context, projectTabList.toMutableList())
        this.recyclerView.adapter = adapter
        recyclerView.refreshComplete()
    }

    override fun setLayoutManager() {
//        super.setLayoutManager()
        Logger.i("设置布局")
        this.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
    }

    override fun createPresenter(): ProjectContract.Presenter {
        return ProjectPresenterImpl().apply { view = this@ProjectFragment }
    }

}