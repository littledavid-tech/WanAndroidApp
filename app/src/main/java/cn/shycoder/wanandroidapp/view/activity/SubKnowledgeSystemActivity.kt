package cn.shycoder.wanandroidapp.view.activity

import android.content.Context
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import butterknife.BindView
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.entity.KnowledgeNode
import cn.shycoder.wanandroidapp.presenter.SubKnowledgeSystemPresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.SubKnowledgeSystemContract
import cn.shycoder.wanandroidapp.view.BaseToolBarActivity

class SubKnowledgeSystemActivity
    : BaseToolBarActivity<SubKnowledgeSystemContract.Presenter>(),
        SubKnowledgeSystemContract.View {

    @BindView(R.id.subknowledge_system_vpKnowledgeType)
    lateinit var vpKnowledgeType: ViewPager

    @BindView(R.id.tabLayout)
    lateinit var tabLayout: TabLayout

    override fun doInit() {
        super.doInit()
        this.presenter!!.loadTabs(this.intent)
    }


    override fun inittablayout(list: List<String>) {
        for (item in list) {
            tabLayout.addTab(tabLayout.newTab().setText(item))
        }
    }

    override fun initViewpager() {
    }


    override fun createPresenter(): SubKnowledgeSystemContract.Presenter {
        return SubKnowledgeSystemPresenterImpl().apply { view = this@SubKnowledgeSystemActivity }
    }

    override fun getToolbarTitle(): String {
        return intent.getStringExtra("parentTitle")
    }

    companion object {
        /**
         * 开启Activity
         * @param context 上下文对象
         * @param parentTitle 一级知识点的名称，将作为Activity的标题
         * @param knowledgeNodes 二级知识点的数组对象
         * */
        fun show(context: Context, parentTitle: String, knowledgeNodes: Array<KnowledgeNode>) {
            val intent = Intent(context, SubKnowledgeSystemActivity::class.java)
            intent.putExtra("parentTitle", parentTitle)
            intent.putExtra("knowledgeNodes", knowledgeNodes)
        }
    }
}
