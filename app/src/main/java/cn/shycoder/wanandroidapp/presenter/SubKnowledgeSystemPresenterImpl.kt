package cn.shycoder.wanandroidapp.presenter

import android.content.Intent
import cn.shycoder.wanandroidapp.model.bean.KnowledgeSystemTab
import cn.shycoder.wanandroidapp.model.entity.KnowledgeNode
import cn.shycoder.wanandroidapp.presenter.base.BasePresenter
import cn.shycoder.wanandroidapp.presenter.contract.SubKnowledgeSystemContract
import cn.shycoder.wanandroidapp.view.activity.SubKnowledgeSystemActivity

/**
 * Created by ShyCoder on 11/13/2018.
 */
class SubKnowledgeSystemPresenterImpl()
    : BasePresenter<SubKnowledgeSystemContract.View>(), SubKnowledgeSystemContract.Presenter {

    override fun loadTabs(intent: Intent) {
        //从Intent中获取子节点的数据
        val list = mutableListOf<KnowledgeSystemTab>()
        val array = intent.getParcelableArrayExtra(
                SubKnowledgeSystemActivity.INTENT_EXTRA_KNOWLEDGE_NODES)
        for (item in array) {
            val knowledgeNode = item as KnowledgeNode
            val tab = KnowledgeSystemTab(knowledgeNode.id, knowledgeNode.name!!)
            list.add(tab)
        }
        view!!.initTabLayout(list)
        view!!.initViewpager()
    }
}