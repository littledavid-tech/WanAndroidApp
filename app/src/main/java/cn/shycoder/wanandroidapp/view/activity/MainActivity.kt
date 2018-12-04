package cn.shycoder.wanandroidapp.view.activity

import android.content.Context
import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import butterknife.BindString
import butterknife.BindView
import cn.shycoder.wanandroidapp.view.BaseToolBarActivity
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.presenter.HomePresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.HomeContract
import cn.shycoder.wanandroidapp.MyApplication
import com.orhanobut.logger.Logger

class MainActivity :
        BaseToolBarActivity<HomeContract.Presenter>(), HomeContract.View {

    @BindString(R.string.app_name)
    lateinit var appName: String

    @BindString(R.string.main_nav_head_tourist)
    lateinit var tourist: String

    @BindView(R.id.main_dl_parent)
    lateinit var dlParent: DrawerLayout

    @BindView(R.id.main_nav)
    lateinit var nav: NavigationView

    @BindView(R.id.main_bottom_nav)
    lateinit var bottomNav: BottomNavigationView

    lateinit var tvUsername: TextView

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

        tvUsername = nav.getHeaderView(0).findViewById(R.id.main_nav_head_user_name)

        this.presenter?.autoLogin()

        nav.setNavigationItemSelectedListener {
            this.presenter!!.disposeNavEvent(this@MainActivity, it.itemId)
            dlParent.closeDrawer(Gravity.LEFT)
            true
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        reloadNavigationMenu()
    }

    override fun reloadNavigationMenu() {
        val myCollectMenu = nav.menu.findItem(R.id.main_menu_my_collect)
        val loginMenu = nav.menu.findItem(R.id.main_menu_my_login)
        if (null != MyApplication.currentUser) {
            myCollectMenu.isVisible = true
            loginMenu.isVisible = false
            tvUsername.text = MyApplication.currentUser?.username
        } else {
            myCollectMenu.isVisible = false
            loginMenu.isVisible = true
            tvUsername.text = tourist
        }
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
        Logger.d("Create Fragment")

        val transaction = this.mFragmentManager.beginTransaction()
        if (!fragment.isAdded) {
            Logger.d("Add Fragment")
            transaction.add(R.id.main_flContainer, fragment)
        }
        this.presenter?.getFragmentMap()!!.forEach {
            if (null != it.value && fragment != it.value) {
                transaction.hide(it.value)
            }
        }
        transaction.show(fragment)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.main_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)
        presenter?.disposeOptionMenuEvent(this, item!!)
        return true
    }

    companion object {
        fun show(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
