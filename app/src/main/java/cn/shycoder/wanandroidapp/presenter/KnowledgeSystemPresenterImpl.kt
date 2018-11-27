package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.model.api.KnowledgeSystemService
import cn.shycoder.wanandroidapp.presenter.contract.KnowledgeSystemContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ITSoftware on 11/8/2018.
 */
class KnowledgeSystemPresenterImpl() : KnowledgeSystemContract.Presenter {

    override var view: KnowledgeSystemContract.View? = null
    override var disposable: Disposable? = null

    override fun loadMore() {
        loadData()
    }

    override fun refreshData() {
        loadData()
    }

    /**
     * 加载所有的知识体系的节点
     * */
    private fun loadData() {
        KnowledgeSystemService
                .instance
                .getAllKnowledgeNode()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view!!.loadedKnowledge(it.data!!)
                        }
                        ,
                        {

                        }
                        ,
                        {

                        }
                        ,
                        {
                            this.disposable = it
                        })

    }


}