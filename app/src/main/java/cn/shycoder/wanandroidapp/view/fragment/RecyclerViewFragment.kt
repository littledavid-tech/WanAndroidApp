package cn.shycoder.wanandroidapp.view.fragment

import android.support.design.widget.FloatingActionButton
import android.view.View
import butterknife.BindView
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.base.BaseFragment
import cn.shycoder.wanandroidapp.presenter.BaseRecyclerViewContract
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView

/**
 * Created by ITSoftware on 11/6/2018.
 */
abstract class RecyclerViewFragment<T : BaseRecyclerViewContract.BaseRecyclerPresenter>(presenter: T) : BaseFragment(), BaseRecyclerViewContract.BaseRecyclerView {

    private var mPresneter: T = presenter

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: PullLoadMoreRecyclerView

    @BindView(R.id.faBtnTop)
    lateinit var btnReturnTop: FloatingActionButton

    override fun getLayoutResId(): Int {
        return R.layout.recycler_view_fragment
    }

    override fun doInit() {
        setLayoutManager()
    }

    private fun doInitRecyclerView() {
        //设置加载更多
        recyclerView.setOnPullLoadMoreListener(object : PullLoadMoreRecyclerView.PullLoadMoreListener {
            override fun onLoadMore() {
                mPresneter.loadMore()
            }

            override fun onRefresh() {
                mPresneter.refreshData()
            }
        })
        //返回顶部的按钮的点击事件
        btnReturnTop.setOnClickListener { recyclerView.scrollToTop() }
    }


    open fun createRecyclerViewHead(): View? {
        return null
    }

    /**
     * 设置RecyclerView的LayoutManager
     * 默认为是LinearLayout
     * */
    open fun setLayoutManager() {
        recyclerView.setLinearLayout()
    }
}