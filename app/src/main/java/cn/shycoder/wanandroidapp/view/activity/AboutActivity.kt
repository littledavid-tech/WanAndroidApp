package cn.shycoder.wanandroidapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import butterknife.BindString
import butterknife.OnClick
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.presenter.AboutPresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.AboutContract
import cn.shycoder.wanandroidapp.view.BaseToolBarActivity

/**
 * Created by ShyCoder on 12/4/2018.
 */
class AboutActivity
    : BaseToolBarActivity<AboutContract.Presenter>(), AboutContract.View {

    @BindString(R.string.about_activity_title)
    lateinit var activityTitle: String

    override fun getLayoutResId(): Int {
        return R.layout.about_activity
    }

    override fun getToolbarTitle(): String {
        return this.activityTitle
    }

    override fun createPresenter(): AboutContract.Presenter {
        return AboutPresenterImpl().apply { view = this@AboutActivity }
    }

    @OnClick(R.id.about_rlAPI,
            R.id.about_rlAuthor,
            R.id.about_rlGithub)
    fun onClick(view: View) {
        this.presenter?.disposeClickEvent(view.id)
    }

    companion object {
        fun show(context: Context) {
            val intent = Intent(context, AboutActivity::class.java)
            context.startActivity(intent)
        }
    }
}