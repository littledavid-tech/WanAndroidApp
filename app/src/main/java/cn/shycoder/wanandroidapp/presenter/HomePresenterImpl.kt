package cn.shycoder.wanandroidapp.presenter

import android.content.Context
import android.support.v4.app.Fragment
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.SPKeyConst
import cn.shycoder.wanandroidapp.model.api.UserService
import cn.shycoder.wanandroidapp.presenter.contract.HomeContract
import cn.shycoder.wanandroidapp.utils.MyApplication
import cn.shycoder.wanandroidapp.view.activity.LoginActivity
import cn.shycoder.wanandroidapp.view.activity.MyCollectedActivity
import cn.shycoder.wanandroidapp.view.fragment.ArticleFragment
import cn.shycoder.wanandroidapp.view.fragment.KnowledgeSystemFragment
import cn.shycoder.wanandroidapp.view.fragment.ProjectFragment
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class HomePresenterImpl
    : HomeContract.Presenter {

    private var mFragmentMap: HashMap<Int, Fragment?> = hashMapOf()

    override var view: HomeContract.View? = null

    override var disposable: Disposable? = null

    override fun createHomeFragment(menuId: Int): Fragment {
        var fragment = mFragmentMap[menuId]
        if (null == fragment) {
            fragment = when (menuId) {
                R.id.main_bottom_nav_menuHome -> {
                    ArticleFragment()
                }
                R.id.main_bottom_nav_menuKnowledge -> {
                    KnowledgeSystemFragment()
                }
                R.id.main_bottom_nav_menuProject -> {
                    ProjectFragment()
                }
                else -> {
                    throw IllegalArgumentException("Unknown menu id!")
                }
            }
            mFragmentMap[menuId] = fragment
        }
        return fragment
    }

    override fun getFragmentMap(): Map<Int, Fragment?> {
        return mFragmentMap
    }

    override fun disposeNavEvent(context: Context, menuId: Int) {
        when (menuId) {
            R.id.main_menu_my_login -> {//登录按钮的点击事件
                LoginActivity.show(context)
            }
            R.id.main_menu_my_logout -> {//Logout
                MyApplication.currentUser = null
                //移除Cookie和用户账号密码信息
                MyApplication.removeSPByKey(SPKeyConst.sp_key_cookie)
                MyApplication.removeSPByKey(SPKeyConst.sp_key_username)
                MyApplication.removeSPByKey(SPKeyConst.sp_key_password)
            }
            R.id.main_menu_my_collect -> {//我的收藏
                MyCollectedActivity.show(context)
            }
            else -> {
                Logger.e("Unknown option")
            }
        }
    }

    override fun autoLogin() {
        Logger.i("Auto login")
        val username = MyApplication.getStringFromSP(SPKeyConst.sp_key_username)
        val pwd = MyApplication.getStringFromSP(SPKeyConst.sp_key_password)
        if (username.isEmpty() || pwd.isEmpty()) {
            MyApplication.currentUser = null
            return
        }
        UserService
                .instance
                .login(username, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    MyApplication.currentUser = if (it.errorCode == 0) it.data else null
                    view?.reloadNavigationMenu()
                }, {
                    it.printStackTrace()
                })
    }

    override fun onDestroy() {
        mFragmentMap.clear()
        super.onDestroy()
    }


}