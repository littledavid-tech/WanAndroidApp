package cn.shycoder.wanandroidapp.view.activity

import android.content.Context
import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import butterknife.BindString
import butterknife.BindView
import cn.shycoder.wanandroidapp.view.BaseToolBarActivity
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.presenter.HomePresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.HomeContract
import com.orhanobut.logger.Logger

class MainActivity :
        BaseToolBarActivity<HomeContract.Presenter>(), HomeContract.View {


    @BindString(R.string.app_name)
    lateinit var appName: String

//    @BindString(R.string.main_bottom_nav_home)
//    lateinit var bottom_nav_home: String
//
//    @BindString(R.string.main_bottom_nav_knowledge_system)
//    lateinit var bottom_nav_knowledge: String
//
//    @BindString(R.string.main_bottom_nav_project)
//    lateinit var bottom_nav_project: String

    @BindView(R.id.main_dl_parent)
    lateinit var dlParent: DrawerLayout

    @BindView(R.id.main_nav)
    lateinit var nav: NavigationView

    @BindView(R.id.main_bottom_nav)
    lateinit var bottomNav: BottomNavigationView

    private lateinit var mFragmentManager: FragmentManager

    override fun getToolbarTitle(): String {
        return appName
    }

    override fun getLayoutResId(): Int {
        return R.layout.main_activity
    }

    override fun doInit() {
        super.doInit()
        //设置Home的图标
        this.actionBar.setHomeAsUpIndicator(R.drawable.main_nav_menu)
        mFragmentManager = this.supportFragmentManager
        doInitBottomNav()
    }

    /**
     * 初始化Bottom Menu
     * */
    private fun doInitBottomNav() {
        bottomNav.setOnNavigationItemSelectedListener {
            Logger.i("Menu Title:${it.title}")
            showFragment(presenter?.createHomeFragment(it.itemId)!!)
            true
        }
        bottomNav.selectedItemId = R.id.main_bottom_nav_menuHome
    }

    override fun onHomeSelected() {
        super.onHomeSelected()
        this.dlParent.openDrawer(Gravity.LEFT)
    }

    override fun canBack(): Boolean {
        return false
    }

    override fun createPresenter(): HomeContract.Presenter {
        return HomePresenterImpl().apply { view = this@MainActivity }
    }

    /**
     * 将碎片显示处理啊
     * */
    override fun showFragment(fragment: Fragment) {
        val transaction = this.mFragmentManager.beginTransaction()
        transaction.add(R.id.main_flContainer, fragment)
        transaction.attach(fragment)
        transaction.show(fragment)
        this.presenter?.getFragmentMap()!!.forEach {
            if (null != it.value && fragment != it.value) {
                transaction.detach(it.value)
            }
        }
        transaction.commit()
    }

    companion object {
        fun show(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
