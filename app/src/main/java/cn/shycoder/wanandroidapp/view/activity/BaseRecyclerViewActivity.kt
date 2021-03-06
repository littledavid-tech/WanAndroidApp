package cn.shycoder.wanandroidapp.view.activity

import android.os.Build
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AbsListView
import butterknife.BindView
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.presenter.contract.BaseRecyclerViewContract
import cn.shycoder.wanandroidapp.view.BaseToolBarActivity
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.orhanobut.logger.Logger


abstract class BaseRecyclerViewActivity<T : BaseRecyclerViewContract.Presenter<*>>
    : BaseToolBarActivity<T>(), BaseRecyclerViewContract.View {

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: XRecyclerView

    @BindView(R.id.faBtnTop)
    lateinit var btnReturnTop: FloatingActionButton

    override fun getLayoutResId(): Int {
        return R.layout.recycler_view_activity
    }

    override fun doInit() {
        super.doInit()
        setLayoutManager()
        doInitReturnTopButton()
        this.createPresenter()
        doInitRecyclerView()
        this.presenter!!.loadMore()
    }

    /**
     * 是否启用加载更多
     * */
    override fun enableLoadMore(boolean: Boolean) {
        this.recyclerView.setLoadingMoreEnabled(boolean)
    }

    /**
     * 以平滑的方式滑动到顶部
     * */
    override fun scrollToTop() {
        this.recyclerView.smoothScrollToPosition(0)
    }

    /**
     * 设置RecyclerView的LayoutManager
     * */
    open fun setLayoutManager() {
        this.recyclerView.layoutManager = LinearLayoutManager(this)
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

    /**
     * 为返回顶部按钮注册事件
     * */
    private fun doInitReturnTopButton() {
        this.btnReturnTop.setOnClickListener { scrollToTop() }
    }


}