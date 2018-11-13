package cn.shycoder.wanandroidapp.presenter

import android.content.Intent
import cn.shycoder.wanandroidapp.presenter.contract.SubKnowledgeSystemContract
import io.reactivex.disposables.Disposable

/**
 * Created by ShyCoder on 11/13/2018.
 */
class SubKnowledgeSystemPresenterImpl() : SubKnowledgeSystemContract.Presenter {

    override var view: SubKnowledgeSystemContract.View? = null
    override var disposable: Disposable? = null

    override fun loadTabs(intent: Intent) {
//        view!!.inittablayout()
    }
}