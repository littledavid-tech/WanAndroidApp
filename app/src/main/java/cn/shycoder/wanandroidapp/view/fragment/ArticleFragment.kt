package cn.shycoder.wanandroidapp.view.fragment

import android.view.View
import android.view.ViewGroup
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.adapter.recyclerview.ArticleAdapter
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.model.entity.HomeBanner
import cn.shycoder.wanandroidapp.presenter.ArticlePresenterImpl
import cn.shycoder.wanandroidapp.presenter.contract.ArticleContract
import cn.shycoder.wanandroidapp.utils.CommonUtils
import cn.shycoder.wanandroidapp.view.NetworkImageHolderView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.bigkoo.convenientbanner.holder.Holder
import com.orhanobut.logger.Logger

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
        //初始化ConvenientBanner
        val banner = ConvenientBanner<HomeBanner>(this.context)
        val layoutParams = ViewGroup.LayoutParams(-1, 500)
        banner.layoutParams = layoutParams
        banner.setPages(object : CBViewHolderCreator {
            override fun createHolder(itemView: View?): Holder<*> {
                return NetworkImageHolderView(itemView)
            }
            override fun getLayoutId(): Int {
                return R.layout.banner_image_view
            }
        }, banners).setOnItemClickListener { position ->
            CommonUtils.openWebSiteInBrowser(context, banners[position].url)
        }.setPageIndicator(intArrayOf(
                R.drawable.banner_page_indicator_unselected_shape,
                R.drawable.banner_page_indicator_selected_shape))

        this.addHeaderView(banner)

        banner.startTurning(2000)
    }

    override fun createPresenter(): ArticleContract.Presenter {
        return ArticlePresenterImpl(this.context).apply { view = this@ArticleFragment }
    }


}