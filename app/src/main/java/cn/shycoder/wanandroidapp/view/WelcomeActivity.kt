package cn.shycoder.wanandroidapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import butterknife.BindView
import cn.shycoder.wanandroidapp.BaseActivity
import cn.shycoder.wanandroidapp.R


class WelcomeActivity : BaseActivity() {

    @BindView(R.id.tv)
    lateinit var tv: TextView

    override fun getLayoutResId(): Int {
        return R.layout.welcome_activity
    }

    /**
     * 显示一个并没有什么卵用的欢迎界面
     * 浪费2秒时间
     * */
    override fun doInit() {
        Handler().postDelayed({
            MainActivity.show(this)
            this.finish()
        }, 2000)
    }

    override fun onBackPressed() {

    }
}
