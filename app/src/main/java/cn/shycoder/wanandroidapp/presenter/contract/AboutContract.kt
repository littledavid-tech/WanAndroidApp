package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.presenter.base.BaseContract

/**
 * Created by ShyCoder on 12/4/2018.
 */
interface AboutContract {
    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {
        fun openGithub(url: String)

        fun openAPI(url: String)

        fun openAuthorHomePage(url: String)

        fun disposeClickEvent(id: Int)
    }
}