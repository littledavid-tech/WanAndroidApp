package cn.shycoder.wanandroidapp.adapter.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.utils.MyApplication
import cn.shycoder.wanandroidapp.view.activity.ArticleDetailActivity
import com.bumptech.glide.Glide

/**
 * 首页文章的适配器
 * */
class ArticleAdapter(context: Context, list: MutableList<Article>)
    : BaseRecyclerViewAdapter<Article, ArticleAdapter.ViewHolder>(context, list) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(MyApplication.context)
                .inflate(R.layout.recycler_view_list_item_home_article
                        , parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = list[position]
        holder!!.article = item
        holder.tvTitle.text = item.title
        holder.tvAuthor.text = item.author
        holder.tvKnowledgeSystem.text = "${item.superChapterName}/${item.chapterName}"
        holder.tvKnowledgeSystem.tag = "${item.superChapterId}/${item.superChapterId}"
        holder.tvTime.text = item.niceDate

        holder.tvTag.visibility = View.VISIBLE
        if (item.tags?.isNotEmpty()!!) {
            holder.tvTag.text = item.tags!![0].name
        } else {
            holder.tvTag.visibility = View.GONE
        }

        //根据是否收藏，设置不同的 Drawable
        val isCollect = null != MyApplication.currentUser &&
                null != MyApplication.currentUser!!.collectIds &&
                MyApplication.currentUser!!.collectIds!!.contains(item.id)

        if (item.isCollect || isCollect) {
            item.isCollect = true
            Glide.with(MyApplication.context).load(R.drawable.app_like).into(holder.ivLike)
        } else {
            Glide.with(MyApplication.context).load(R.drawable.app_unlike).into(holder.ivLike)
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.home_article_item_tvTag)
        lateinit var tvTag: TextView

        @BindView(R.id.home_article_item_tvTime)
        lateinit var tvTime: TextView

        @BindView(R.id.home_article_item_tvTitle)
        lateinit var tvTitle: TextView

        @BindView(R.id.home_article_item_tvAuthor)
        lateinit var tvAuthor: TextView

        @BindView(R.id.home_article_item_tvKnowledgeSystem)
        lateinit var tvKnowledgeSystem: TextView

        @BindView(R.id.home_article_item_ivLike)
        lateinit var ivLike: ImageView

        lateinit var article: Article

        init {
            ButterKnife.bind(this, itemView!!)
            itemView.setOnClickListener {
                ArticleDetailActivity.show(MyApplication.context, article)
            }
        }
    }
}