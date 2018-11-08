package cn.shycoder.wanandroidapp.view.activity

import android.os.Handler
import android.widget.TextView
import butterknife.BindView
import cn.shycoder.wanandroidapp.view.BaseActivity
import cn.shycoder.wanandroidapp.R

class WelcomeActivity : BaseActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.welcome_activity
    }

    /**
     * 显示一个并没有什么卵用的欢迎界面
     * 浪费2秒时间
     * */
    override fun doInit() {
//        Handler().postDelayed({
//            MainActivity.show(this)
//            this.finish()
//        }, 2000)
        MainActivity.show(this)
    }

    override fun onBackPressed() {

    }
}
