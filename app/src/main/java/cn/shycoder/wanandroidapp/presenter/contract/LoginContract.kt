package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.model.entity.UserInfo

/**
 * Created by ShyCoder on 11/24/2018.
 */
interface LoginContract {
    interface View : BaseContract.View {
        fun loginSuccessful(userInfo: UserInfo)
        fun loginFailed()
        fun cannotBeNull()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun login(username: String, password: String)
    }
}