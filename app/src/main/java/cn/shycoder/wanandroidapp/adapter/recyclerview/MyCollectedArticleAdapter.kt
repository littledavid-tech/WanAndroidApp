package cn.shycoder.wanandroidapp.adapter.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.view.activity.ArticleDetailActivity

/**
 * Created by ShyCoder on 11/28/2018.
 */
class MyCollectedArticleAdapter(context: Context, list: MutableList<Article>)
    : BaseRecyclerViewAdapter<Article, MyCollectedArticleAdapter.ViewHolder>(context, list) {

    interface OnDeleteListener {
        fun onRemove(itemView: View, position: Int, article: Article)
    }

    var onRemoveCollectedListener: OnDeleteListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(context)
                .inflate(R.layout.recycler_view_list_item_collected_article, parent, false)
        return ViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = list[position]
        holder!!.article = item
        holder.tvAuthor.text = item.author
        holder.tvTitle.text = item.title
//        holder.itemView.setOnLongClickListener {
//            this.onRemoveCollectedListener?.onRemove(holder.itemView, position, item)
//            true
//        }
    }

    class ViewHolder(context: Context, itemView: View?)
        : RecyclerView.ViewHolder(itemView) {

        lateinit var article: Article

        @BindView(R.id.my_collected_item_tvArticleTitle)
        lateinit var tvTitle: TextView

        @BindView(R.id.my_collected_item_tvAuthor)
        lateinit var tvAuthor: TextView

        init {
            ButterKnife.bind(this, itemView!!)
            itemView.setOnClickListener {
                ArticleDetailActivity.show(context, article)
            }
        }
    }
}