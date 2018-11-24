package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.presenter.contract.LoginContract
import io.reactivex.disposables.Disposable

/**
 * Created by ShyCoder on 11/24/2018.
 */
class LoginPresenterImpl() : LoginContract.Presenter {

    override var view: LoginContract.View? = null
    override var disposable: Disposable? = null

    override fun login(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            view!!.cannotBeNull()
            return
        }
    }
}