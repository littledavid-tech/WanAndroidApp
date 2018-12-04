package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.presenter.base.BasePresenter
import cn.shycoder.wanandroidapp.presenter.contract.AboutContract
import cn.shycoder.wanandroidapp.utils.CommonUtils
import cn.shycoder.wanandroidapp.MyApplication

/**
 * Created by ShyCoder on 12/4/2018.
 */
class AboutPresenterImpl
    : BasePresenter<AboutContract.View>(), AboutContract.Presenter {

    private val mAPI = "http://wanandroid.com/"
    private val mGithub = "https://github.com/littledavid-tech/WanAndroidApp"
    private val mBlog = "http://shycoder.cn/"

    override fun disposeClickEvent(id: Int) {
        when (id) {
            R.id.about_rlAPI -> {
                openAPI(mAPI)
            }
            R.id.about_rlAuthor -> {
                openAuthorHomePage(mBlog)
            }
            R.id.about_rlGithub -> {
                openGithub(mGithub)
            }
        }
    }

    override fun openGithub(url: String) {
        CommonUtils.openWebSiteInBrowser(MyApplication.context,url)
    }

    override fun openAPI(url: String) {
        CommonUtils.openWebSiteInBrowser(MyApplication.context,url)
    }

    override fun openAuthorHomePage(url: String) {
        CommonUtils.openWebSiteInBrowser(MyApplication.context,url)
    }

}