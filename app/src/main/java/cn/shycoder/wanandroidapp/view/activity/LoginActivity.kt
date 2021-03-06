package cn.shycoder.wanandroidapp.view.activity

import android.content.Context
import android.content.Intent
import android.support.design.widget.TextInputEditText
import android.widget.Button
import butterknife.BindView
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.entity.User
import cn.shycoder.wanandroidapp.presenter.LoginPresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.LoginContract
import cn.shycoder.wanandroidapp.MyApplication
import cn.shycoder.wanandroidapp.utils.ToastUtils
import cn.shycoder.wanandroidapp.view.BaseToolBarActivity


class LoginActivity : BaseToolBarActivity<LoginPresenterImpl>(), LoginContract.View {

    @BindView(R.id.login_etUsername)
    lateinit var etUsername: TextInputEditText

    @BindView(R.id.login_etPassword)
    lateinit var etPassword: TextInputEditText

    @BindView(R.id.login_btnLogin)
    lateinit var btnLogin: Button

    override fun doInit() {
        super.doInit()
        this.btnLogin.setOnClickListener {
            this.presenter?.login(etUsername.text.toString(), etPassword.text.toString())
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.login_activity
    }

    override fun getToolbarTitle(): String {
        return "Login"
    }

    override fun loginSuccessful(userInfo: User) {
        ToastUtils.show(R.string.login_msg_login_successfully)
        MyApplication.currentUser = userInfo
        this.finish()
    }

    override fun loginFailed() {
        ToastUtils.show(R.string.login_msg_login_failed)
        this.etPassword.setText("")
        this.etUsername.setText("")
        MyApplication.currentUser = null
    }

    override fun cannotBeNull() {
        ToastUtils.show(R.string.login_msg_null_username_or_password)
    }

    override fun createPresenter(): LoginPresenterImpl {
        return LoginPresenterImpl().apply { view = this@LoginActivity }
    }

    companion object {
        fun show(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

}