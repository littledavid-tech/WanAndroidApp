package cn.shycoder.wanandroidapp.view.fragment

import android.view.ViewGroup
import cn.shycoder.wanandroidapp.GlideImageLoader
import cn.shycoder.wanandroidapp.adapter.recyclerview.ArticleAdapter
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.model.entity.HomeBanner
import cn.shycoder.wanandroidapp.presenter.ArticlePresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.ArticleContract
import com.orhanobut.logger.Logger
import com.youth.banner.Banner
import com.youth.banner.BannerConfig

/**
 * 显示首页文章的碎片
 */
class ArticleFragment
    : BaseRecyclerViewFragment<ArticleContract.Presenter>(),
        ArticleContract.View {

    var adapter: ArticleAdapter? = null

    override fun doInit() {
        super.doInit()
        Logger.i("Home Article Load")
        presenter?.loadMore()
        presenter?.loadBanner()
    }

    override fun loadedData(list: List<Article>) {
        if (adapter == null) {
            adapter = ArticleAdapter(this.context, list.toMutableList())
            recyclerView.adapter = adapter
            recyclerView.loadMoreComplete()
        } else {
            adapter!!.addList(list)
            recyclerView.loadMoreComplete()
        }
    }

    override fun refreshedData(list: List<Article>) {
        adapter = null
        adapter = ArticleAdapter(this.context, list.toMutableList())
        recyclerView.adapter = adapter
        recyclerView.refreshComplete()
    }

    /**
     * 加载Banners
     * */
    override fun loadedBanner(banners: List<HomeBanner>) {
        //TODO Banner的指示器没有正常的加载出来
        val imgUrlList = banners.map { it.imagePath }
        val titleList = banners.map { it.title }

        val banner = Banner(this.context)
        val layoutParams = ViewGroup.LayoutParams(-1, 500)
        banner.layoutParams = layoutParams;

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setImageLoader(GlideImageLoader())
        //设置显示标题的格式为 [标题+数字]
        banner.setImages(imgUrlList)
        banner.setBannerTitles(titleList)
        //为Banner 注册点击事件
        banner.setOnBannerListener { position ->
            val homeBanner = banners[position]
            this.presenter?.disposeBannerClickEvent(homeBanner)
        }
        this.addHeaderView(banner)
        banner.start()
    }

    override fun createPresenter(): ArticleContract.Presenter {
        return ArticlePresenterImpl(this.context).apply { view = this@ArticleFragment }
    }


}