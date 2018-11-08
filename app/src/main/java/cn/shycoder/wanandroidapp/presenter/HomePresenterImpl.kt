package cn.shycoder.wanandroidapp.presenter

import android.support.v4.app.Fragment
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.presenter.contract.HomeContract
import cn.shycoder.wanandroidapp.view.fragment.ArticleFragment
import io.reactivex.disposables.Disposable


class HomePresenterImpl()
    : HomeContract.Presenter {

    private var mFragmentMap: HashMap<Int, Fragment?> = hashMapOf()

    override var view: HomeContract.View? = null

    override var disposable: Disposable? = null

    override fun createHomeFragment(menuId: Int): Fragment {
        var fragment = mFragmentMap[menuId]
        if (null == fragment) {
            when (menuId) {
                R.id.main_bottom_nav_menuHome -> {
                    fragment = ArticleFragment()
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

    override fun onDestroy() {
        super.onDestroy()
        mFragmentMap.clear()
    }
}