package cn.shycoder.wanandroidapp.presenter.contract

import android.content.Intent
import cn.shycoder.wanandroidapp.model.bean.KnowledgeSystemTab
import cn.shycoder.wanandroidapp.presenter.base.BaseContract

/**
 * Created by ShyCoder on 11/13/2018.
 */
interface SubKnowledgeSystemContract {
    interface View : BaseContract.View {

        /**
         * 初始化TabLayout
         * */
        fun initTabLayout(list: List<KnowledgeSystemTab>)

        fun initViewpager()
    }

    interface Presenter : BaseContract.Presenter<View> {
        /**
         *
         * */
        fun loadTabs(intent: Intent)
    }
}