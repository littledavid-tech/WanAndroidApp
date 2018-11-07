package cn.shycoder.wanandroidapp.view.fragment

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.view.BaseFragment
import cn.shycoder.wanandroidapp.presenter.contract.BaseRecyclerViewContract
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView

/**
 * 具有RecyclerView的Fragment的基类，所有的具有RecyclerView的Fragment都从此类继承
 * */
abstract class BaseRecyclerViewFragment<T : BaseRecyclerViewContract.BaseRecyclerPresenter<*>>
    : BaseFragment(), BaseRecyclerViewContract.BaseRecyclerView {

    private var mPresenter: T? = null

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: PullLoadMoreRecyclerView

    @BindView(R.id.faBtnTop)
    lateinit var btnReturnTop: FloatingActionButton

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
        mPresenter = null
    }

    override fun getLayoutResId(): Int {
        return R.layout.recycler_view_fragment
    }

    override fun doInit() {
        this.mPresenter = createPresenter()
        setLayoutManager()
        doInitRecyclerView()
    }

    /**
     * 初始化RecyclerView的相关事件和属性
     * */
    private fun doInitRecyclerView() {

        //设置加载更和刷新的事件
        recyclerView.setOnPullLoadMoreListener(object : PullLoadMoreRecyclerView.PullLoadMoreListener {
            override fun onLoadMore() {
                mPresenter?.loadMore()
            }

            override fun onRefresh() {
                mPresenter?.refreshData()
            }
        })

        //返回顶部的按钮的点击事件
        btnReturnTop.setOnClickListener { scrollToTop() }

        //添加RecyclerView的滑动事件,用来控制 滑动到顶部的按钮的显示
        recyclerView.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 10) {
                    if (btnReturnTop.visibility == View.VISIBLE) {
                        return
                    }
                    //显示Button
                    btnReturnTop.visibility = View.VISIBLE

                    //开启一个属性动画 Alpha 从 0 - 1
                    btnReturnTop.animate()
                            .setDuration(200)
                            .alpha(1F)
                            .start()
                } else {
                    if (btnReturnTop.visibility == View.INVISIBLE) {
                        return
                    }
                    //显示Button
                    btnReturnTop.visibility = View.INVISIBLE
                    //开启一个属性动画 Alpha 从 0 - 1
                    btnReturnTop.animate()
                            .setDuration(200)
                            .alpha(0F)
                            .start()
                }
            }
        })

    }

    override fun scrollToTop() {
        recyclerView.scrollToTop()
    }

    override fun enableLoadMore(boolean: Boolean) {
        recyclerView.setIsLoadMore(boolean)
    }

    /**
     * 设置RecyclerView的LayoutManager
     * 默认为是LinearLayout
     * */
    open fun setLayoutManager() {
        recyclerView.setLinearLayout()
    }

    abstract fun <T> createPresenter(): T
}