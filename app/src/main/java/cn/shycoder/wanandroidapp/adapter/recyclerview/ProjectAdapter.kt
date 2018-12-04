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
import cn.shycoder.wanandroidapp.MyApplication
import cn.shycoder.wanandroidapp.view.activity.ArticleDetailActivity
import com.bumptech.glide.Glide

/**
 * Created by ShyCoder on 11/15/2018.
 */
class ProjectAdapter(context: Context, list: MutableList<Article>)
    : BaseRecyclerViewAdapter<Article, ProjectAdapter.ViewHolder>(context, list) {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(MyApplication.context)
                .inflate(
                        R.layout.recycler_view_grid_item_project
                        , parent
                        , false)
        return ViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = list[position]
        holder!!.article = item

        holder.tvAuthor.text = item.author
        holder.tvTime.text = item.niceDate
        holder.tvTitle.text = item.title

        Glide.with(MyApplication.context)
                .load(item.envelopePic)
                .into(holder.ivCoverImg)

        //根据是否收藏，设置不同的 Drawable
        if (item.isCollect ||
                (null != MyApplication.currentUser && MyApplication.currentUser!!.isArticleCollected(item.id))) {
            Glide.with(MyApplication.context).load(R.drawable.app_like).into(holder.ivLike)
        } else {
            Glide.with(MyApplication.context).load(R.drawable.app_unlike).into(holder.ivLike)
        }
    }

    class ViewHolder(context: Context, itemView: View?) : RecyclerView.ViewHolder(itemView) {

        lateinit var article: Article

        @BindView(R.id.main_project_item_tvProjectAuthor)
        lateinit var tvAuthor: TextView

        @BindView(R.id.main_project_item_tvTime)
        lateinit var tvTime: TextView

        @BindView(R.id.main_project_item_tvTitle)
        lateinit var tvTitle: TextView

        @BindView(R.id.main_project_item_ivCoverImg)
        lateinit var ivCoverImg: ImageView

        @BindView(R.id.main_project_item_ivLike)
        lateinit var ivLike: ImageView

        init {
            ButterKnife.bind(this, itemView!!)
            itemView!!.setOnClickListener {
                ArticleDetailActivity.show(context, article)
            }
        }

    }
}