package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.model.api.ProjectService
import cn.shycoder.wanandroidapp.model.bean.ProjectTab
import cn.shycoder.wanandroidapp.presenter.base.BasePresenter
import cn.shycoder.wanandroidapp.presenter.contract.ProjectContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProjectPresenterImpl()
    : BasePresenter<ProjectContract.View>(), ProjectContract.Presenter {

    override fun loadMore() {
        loadProjectTabList()
    }

    override fun refreshData() {
        loadProjectTabList()
    }

    override fun loadProjectTabList() {
        ProjectService
                .instance
                .getProjectCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val projectTabList = mutableListOf<ProjectTab>()
                    for (item in it!!.data!!) {
                        projectTabList.add(ProjectTab(item.id!!, item.name!!))
                    }
                    this.view!!.loadedProjectCategory(projectTabList)
                }, {
                    this.disposeException(it)
                    it.printStackTrace()
                }, {

                }, {
                    this.addDisposable(it)
                })
    }

}