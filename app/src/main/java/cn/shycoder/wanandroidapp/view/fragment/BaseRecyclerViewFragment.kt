package cn.shycoder.wanandroidapp.view.fragment

import android.os.Build
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AbsListView
import butterknife.BindView
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.view.BaseFragment
import cn.shycoder.wanandroidapp.presenter.contract.BaseRecyclerViewContract
import cn.shycoder.wanandroidapp.MyApplication
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.orhanobut.logger.Logger

/**
 * 具有RecyclerView的Fragment的基类，所有的具有RecyclerView的Fragment都从此类继承
 * */
abstract class BaseRecyclerViewFragment<T : BaseRecyclerViewContract.Presenter<*>>
    : BaseFragment<T>(), BaseRecyclerViewContract.View {

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: XRecyclerView

    @BindView(R.id.faBtnTop)
    lateinit var btnReturnTop: FloatingActionButton

    override fun onDestroy() {
        recyclerView.destroy()
        super.onDestroy()
    }

    override fun getLayoutResId(): Int {
        return R.layout.recycler_view_fragment
    }

    override fun doInit() {
        this.presenter = createPresenter()
        setLayoutManager()
        btnReturnTop.setOnClickListener { scrollToTop() }
        doInitRecyclerView()
    }

    /**
     * 初始化RecyclerView的相关事件和属性
     * */
    private fun doInitRecyclerView() {
        recyclerView.setPullRefreshEnabled(true)
        recyclerView.setLoadingMoreEnabled(true)
        recyclerView.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                presenter?.loadMore()
                Logger.d("Load More!")
            }

            override fun onRefresh() {
                presenter?.refreshData()
                Logger.d("Load refresh!")
            }
        })

        //为Return Top Button 添加一个显示与隐藏的动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            btnReturnTop.visibility = View.INVISIBLE

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                        return
                    }
                    if (recyclerView!!.layoutManager is LinearLayoutManager) {
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        if (layoutManager.findFirstVisibleItemPosition() > 1) {
                            showReturnTopButton()
                        } else {
                            hideReturnTopButton()
                        }
                    }
                }
            })
        }
    }

    override fun scrollToTop() {
        recyclerView.smoothScrollToPosition(0)
    }

    override fun enableLoadMore(boolean: Boolean) {
        recyclerView.setLoadingMoreEnabled(boolean)
    }

    /**
     * 设置RecyclerView的LayoutManager
     * 默认为是LinearLayout
     * */
    open fun setLayoutManager() {
        recyclerView.layoutManager = LinearLayoutManager(MyApplication.context)
    }

    /**
     * 显示返回顶部的Button
     * */
    open fun showReturnTopButton() {
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
    }

    /**
     * 隐藏返回顶部的Button
     * */
    open fun hideReturnTopButton() {
        if (btnReturnTop.visibility == View.INVISIBLE) {
            return
        }

        //开启一个属性动画 Alpha 从 0 - 1
        btnReturnTop.animate()
                .setDuration(200)
                .alpha(0F)
                .withEndAction({
                    btnReturnTop.visibility = View.INVISIBLE
                })
                .start()
    }

    /**
     * 为RecyclerView添加头部
     * */
    fun addHeaderView(headerView: View) {
        this.recyclerView.addHeaderView(headerView)
    }


}