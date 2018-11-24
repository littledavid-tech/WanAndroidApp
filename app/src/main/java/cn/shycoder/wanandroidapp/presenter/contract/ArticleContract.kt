package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.model.entity.HomeBanner

/**
 * Created by ITSoftware on 11/7/2018.
 */
interface ArticleContract {
    interface View : BaseRecyclerViewContract.View {

        fun loadedData(list: List<Article>)

        fun refreshedData(list: List<Article>)

        fun loadedBanner(banners: List<HomeBanner>)
    }

    interface Presenter : BaseRecyclerViewContract.Presenter<View> {

        /**
         * 加载首页的 Banner
         * */
        fun loadBanner()
    }
}