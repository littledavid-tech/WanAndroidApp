package cn.shycoder.wanandroidapp.view.fragment

import android.app.Fragment
import android.support.design.widget.FloatingActionButton
import butterknife.BindView
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.base.BaseFragment
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView
import kotlinx.android.synthetic.main.recycler_view_fragment.view.*

/**
 * Created by ITSoftware on 11/6/2018.
 */
class RecyclerViewFragment : BaseFragment() {

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: PullLoadMoreRecyclerView

    @BindView(R.id.faBtnTop)
    lateinit var btnReturnTop: FloatingActionButton
    
    override fun getLayoutResId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun doInit(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}