package cn.shycoder.wanandroidapp.presenter.contract

import android.content.Intent

/**
 * Created by ShyCoder on 11/13/2018.
 */
interface SubKnowledgeSystemContract {
    interface View : BaseContract.View {

        /**
         * 初始化TabLayout
         * */
        fun inittablayout(list: List<String>)

        fun initViewpager()
    }

    interface Presenter : BaseContract.Presenter<View> {
        /**
         *
         * */
        fun loadTabs(intent: Intent)
    }
}