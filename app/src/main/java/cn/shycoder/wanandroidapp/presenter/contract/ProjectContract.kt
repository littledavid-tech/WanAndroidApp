package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.model.bean.ProjectTab


interface ProjectContract {
    interface View : BaseRecyclerViewContract.View {

        fun loadedProjectCategory(projectTabList: List<ProjectTab>)
    }

    interface Presenter : BaseRecyclerViewContract.Presenter<View> {
        fun loadProjectTabList()
    }
}