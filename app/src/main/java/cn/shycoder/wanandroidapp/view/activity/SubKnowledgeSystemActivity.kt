package cn.shycoder.wanandroidapp.view.activity

import android.content.Context
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import butterknife.BindView
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.adapter.viewpager.KnowledgeSystemArticleFragmentPagerAdapter
import cn.shycoder.wanandroidapp.model.bean.KnowledgeSystemTab
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

    lateinit var tabList: List<KnowledgeSystemTab>

    override fun getLayoutResId(): Int {
        return R.layout.activity_sub_knowledge_system
    }

    override fun doInit() {
        super.doInit()
        this.presenter!!.loadTabs(this.intent)
    }


    override fun initTabLayout(list: List<KnowledgeSystemTab>) {
        tabList = list
        for (item in list) {
            tabLayout.addTab(tabLayout.newTab().setText(item.title).setTag(item.cId))
        }
    }

    override fun initViewpager() {
        val adapter = KnowledgeSystemArticleFragmentPagerAdapter(this.supportFragmentManager, this.tabList)
        vpKnowledgeType.adapter = adapter
    }


    override fun createPresenter(): SubKnowledgeSystemContract.Presenter {
        return SubKnowledgeSystemPresenterImpl().apply { view = this@SubKnowledgeSystemActivity }
    }

    override fun getToolbarTitle(): String {
        return intent.getStringExtra(INTENT_EXTRA_PARENT_TITLE)
    }

    companion object {

        const val INTENT_EXTRA_PARENT_TITLE = "parentTitle"
        const val INTENT_EXTRA_KNOWLEDGE_NODES = "knowledgeNodes"

        /**
         * 开启Activity
         * @param context 上下文对象
         * @param parentTitle 一级知识点的名称，将作为Activity的标题
         * @param knowledgeNodes 二级知识点的数组对象
         * */
        fun show(context: Context, parentTitle: String, knowledgeNodes: Array<KnowledgeNode>) {
            val intent = Intent(context, SubKnowledgeSystemActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARENT_TITLE, parentTitle)
            intent.putExtra(INTENT_EXTRA_KNOWLEDGE_NODES, knowledgeNodes)
            context.startActivity(intent)
        }
    }
}
