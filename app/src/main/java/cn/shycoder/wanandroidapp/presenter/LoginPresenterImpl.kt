package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.SPKeyConst
import cn.shycoder.wanandroidapp.model.api.UserService
import cn.shycoder.wanandroidapp.presenter.base.BasePresenter
import cn.shycoder.wanandroidapp.presenter.contract.LoginContract
import cn.shycoder.wanandroidapp.MyApplication
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenterImpl
    : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun login(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            view!!.cannotBeNull()
            return
        }
        UserService
                .instance
                .login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when {
                        it.errorCode == 0 -> {//登录成功
                            view!!.loginSuccessful(it.data!!)
                            saveUsernameAndPwdIntoSP(username, password)
                        }
                        it.errorCode == -1 -> {//登录失败
                            view!!.loginFailed()
                        }
                        else -> {//未曾处理的错误
                            Logger.e("未处理错误!!")
                        }
                    }
                }, {
                    this.disposeException(it)
                    it.printStackTrace()
                }, {

                }, {
                    this.addDisposable(it)
                })
    }

    /**
     * 本地持久化保存账号和密码
     * */
    private fun saveUsernameAndPwdIntoSP(username: String, pwd: String) {
        MyApplication.putStringToSP(SPKeyConst.sp_key_username, username)
        MyApplication.putStringToSP(SPKeyConst.sp_key_password, pwd)
    }

}