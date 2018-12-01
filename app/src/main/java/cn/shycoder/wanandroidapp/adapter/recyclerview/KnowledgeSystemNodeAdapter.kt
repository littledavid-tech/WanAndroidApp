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
import cn.shycoder.wanandroidapp.model.entity.KnowledgeNode
import cn.shycoder.wanandroidapp.utils.MyApplication
import cn.shycoder.wanandroidapp.utils.MyApplication.Companion.context
import cn.shycoder.wanandroidapp.view.activity.SubKnowledgeSystemActivity

/**
 * Created by ITSoftware on 11/8/2018.
 */
class KnowledgeSystemNodeAdapter(context: Context, list: MutableList<KnowledgeNode>)
    : BaseRecyclerViewAdapter<KnowledgeNode, KnowledgeSystemNodeAdapter.ViewHolder>(context, list) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(MyApplication.context)
                .inflate(R.layout.recycler_view_list_item_knowledge, parent, false)
        return ViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = list[position]
        holder!!.knowledgeNode = item
        holder.tvKnowledgeSystemNodeTitle.text = item.name
    }

    class ViewHolder(context: Context, itemView: View?) : RecyclerView.ViewHolder(itemView) {


        lateinit var knowledgeNode: KnowledgeNode

        @BindView(R.id.knowledge_system_item_tvKnowledgeCategoryName)
        lateinit var tvKnowledgeSystemNodeTitle: TextView

        @BindView(R.id.knowledge_system_item_ivOpen)
        lateinit var ivOpen: ImageView

        init {
            ButterKnife.bind(this, itemView!!)

//            ivOpen.setOnClickListener {
//                SubKnowledgeSystemActivity.show(context,
//                        knowledgeNode.name!!,
//                        knowledgeNode.children!!.toTypedArray())
//            }

            itemView.setOnClickListener {
                SubKnowledgeSystemActivity.show(context,
                        knowledgeNode.name!!,
                        knowledgeNode.children!!.toTypedArray())
            }
        }
    }
}