package cn.shycoder.wanandroidapp.view.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import butterknife.BindView
import cn.shycoder.wanandroidapp.view.BaseActivity
import cn.shycoder.wanandroidapp.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.welcome_activity)
        MainActivity.show(this)
    }

    override fun onBackPressed() {

    }
}
