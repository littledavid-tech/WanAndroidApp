package cn.shycoder.wanandroidapp.base


import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.BindView
import cn.shycoder.wanandroidapp.R

abstract class BaseToolBarActivity : BaseActivity() {
    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar
    lateinit var actionBar: ActionBar

    override fun getLayoutResId(): Int {
        return R.layout.base_toolbar_activity
    }

    override fun doInit() {
        doInitToolbar()
    }

    /**
     * 初始化toolbar
     * */
    private fun doInitToolbar() {

        this.setSupportActionBar(this.toolbar)

        this.actionBar = this.supportActionBar!!
        this.actionBar.title = getToolbarTitle()

        actionBar.setDisplayHomeAsUpEnabled(true)

        if (canBack()) {
            actionBar.setHomeAsUpIndicator(R.drawable.app_toolbar_back)
        }
    }

    abstract fun getToolbarTitle(): String

    /**
     * toolbar上的按钮是否具有返回功能
     * @return 返回为true 则代表点击toolbar上的返回按钮结束当前的activity
     * */
    open fun canBack(): Boolean = true

    /**
     * 当toolbar的Home按钮不具备返回功能的时候(即canBack返回false)执行此方法
     * */
    open fun onHomeSelected() {

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //处理toolbar的back按钮的事件
        when (item!!.itemId) {
            android.R.id.home -> {
                if (canBack()) {
                    this.onDestroy()
                } else {
                    this.onHomeSelected()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
