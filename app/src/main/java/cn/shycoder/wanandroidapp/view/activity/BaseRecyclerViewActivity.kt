package cn.shycoder.wanandroidapp.view.activity

import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.presenter.contract.BaseRecyclerViewContract
import cn.shycoder.wanandroidapp.view.BaseActivity

/**
 * Created by ShyCoder on 11/15/2018.
 */
class BaseRecyclerViewActivity<T : BaseRecyclerViewContract.Presenter<*>>
    : BaseActivity<T>(), BaseRecyclerViewContract.View {

    override fun enableLoadMore(boolean: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun scrollToTop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutResId(): Int {
        return R.layout.recycler_view_fragment
    }

    override fun createPresenter(): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}