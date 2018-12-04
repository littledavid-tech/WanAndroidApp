package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.model.entity.User
import cn.shycoder.wanandroidapp.presenter.base.BaseContract

/**
 * Created by ShyCoder on 11/24/2018.
 */
interface LoginContract {
    interface View : BaseContract.View {
        fun loginSuccessful(userInfo: User)
        fun loginFailed()
        fun cannotBeNull()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun login(username: String, password: String)
    }
}