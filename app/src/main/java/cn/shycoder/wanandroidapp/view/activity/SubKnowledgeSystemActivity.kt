package cn.shycoder.wanandroidapp.view.activity

import android.content.Context
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v4.view.animation.FastOutLinearInInterpolator
import butterknife.BindView
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.entity.KnowledgeNode
import cn.shycoder.wanandroidapp.presenter.contract.BaseContract
import cn.shycoder.wanandroidapp.view.BaseToolBarActivity

class SubKnowledgeSystemActivity : BaseToolBarActivity<BaseContract.Presenter<BaseContract.View>>() {

    @BindView(R.id.subknowledge_system_vpKnowledgeType)
    lateinit var vpKnowledgeType: ViewPager

    @BindView(R.id.tabLayout)
    lateinit var tabLayout: TabLayout

    override fun doInit() {
        super.doInit()
    }

    override fun createPresenter(): BaseContract.Presenter<BaseContract.View> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getToolbarTitle(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        /**
         * 开启Activity
         * @param context 上下文对象
         * @param parentTitle 一级知识点的名称，将作为Activity的标题
         * @param knowledgeNode 二级知识点的数组对象
         * */
        fun show(context: Context, parentTitle: String, knowledgeNodes: Array<KnowledgeNode>) {
            val intent = Intent(context, SubKnowledgeSystemActivity::class.java)
            intent.putExtra("parentTitle", parentTitle)
            intent.putExtra("knowledgeNodes", knowledgeNodes)
        }
    }
}
