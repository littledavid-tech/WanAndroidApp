package cn.shycoder.wanandroidapp.adapter.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.model.entity.CollectedArticle
import retrofit2.http.POST

/**
 * Created by ShyCoder on 11/28/2018.
 */
class MyCollectedArticleAdapter(context: Context, list: MutableList<CollectedArticle>)
    : BaseRecyclerViewAdapter<CollectedArticle, MyCollectedArticleAdapter.ViewHolder>(context, list) {

    interface OnRemoveCallBack {
        fun onRemove(position: Int, collectedArticle: CollectedArticle)
    }

    var onRemoveCallBack: OnRemoveCallBack? = null

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
        //菜单的点击事件
        holder.ivMenu.setOnClickListener {
            //实例化一个popupMenu
            val popupMenu = PopupMenu(this.context, holder.ivMenu)
            MenuInflater(context).inflate(R.menu.my_collected_item_popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                if (it.itemId == R.id.my_collected_item_popup_menu_remove) {
                    this.onRemoveCallBack?.onRemove(position, item)
                }
                true
            }
            popupMenu.show()
        }
    }

    class ViewHolder(context: Context, itemView: View?)
        : RecyclerView.ViewHolder(itemView) {

        lateinit var article: CollectedArticle

        @BindView(R.id.my_collected_item_tvArticleTitle)
        lateinit var tvTitle: TextView

        @BindView(R.id.my_collected_item_tvAuthor)
        lateinit var tvAuthor: TextView

        @BindView(R.id.my_collected_item_ivMenu)
        lateinit var ivMenu: ImageView

        init {
            ButterKnife.bind(this, itemView!!)
        }
    }
}