package cn.shycoder.wanandroidapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.entity.KnowledgeNode
import cn.shycoder.wanandroidapp.utils.MyApplication

/**
 * Created by ITSoftware on 11/8/2018.
 */
class KnowledgeSystemNodeAdapter(list: MutableList<KnowledgeNode>)
    : BaseRecyclerViewAdapter<KnowledgeNode, KnowledgeSystemNodeAdapter.ViewHolder>(list) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(MyApplication.context)
                .inflate(R.layout.recycler_view_item_knowledge, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.knowledge_system_item_tvKnowledgeCategoryName)
        lateinit var tvKnowledgeSystemNodeTitle: TextView

        @BindView(R.id.knowledge_system_item_ivOpen)
        lateinit var ivOpen: ImageView

        init {
            ButterKnife.bind(this, itemView!!)
        }
    }
}