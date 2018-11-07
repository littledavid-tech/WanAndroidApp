package cn.shycoder.wanandroidapp.view.activity

import android.content.Context
import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import butterknife.BindString
import butterknife.BindView
import cn.shycoder.wanandroidapp.base.BaseToolBarActivity
import cn.shycoder.wanandroidapp.R

class MainActivity : BaseToolBarActivity() {

    @BindString(R.string.app_name)
    lateinit var appName: String

    @BindView(R.id.main_dl_parent)
    lateinit var dlParent: DrawerLayout

    @BindView(R.id.main_nav)
    lateinit var nav: NavigationView

    @BindView(R.id.main_tab_layout)
    lateinit var tabLayout: TabLayout

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
    }

    override fun onHomeSelected() {
        super.onHomeSelected()
        this.dlParent.openDrawer(Gravity.LEFT)
    }

    override fun canBack(): Boolean {
        return false
    }

    companion object {
        fun show(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
