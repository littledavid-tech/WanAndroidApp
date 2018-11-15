package cn.shycoder.wanandroidapp.adapter.recyclerview

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.model.bean.ProjectTab
import cn.shycoder.wanandroidapp.utils.MyApplication
import cn.shycoder.wanandroidapp.view.activity.ProjectListActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.orhanobut.logger.Logger
import java.util.*


/**
 * Created by ShyCoder on 11/15/2018.
 */
class ProjectCategoryAdapter(context: Context, list: MutableList<ProjectTab>)
    : BaseRecyclerViewAdapter<ProjectTab, ProjectCategoryAdapter.ViewHolder>(context, list) {

    private val bgs = arrayOf(
            R.drawable.main_project_category_bg_1,
            R.drawable.main_project_category_bg_2,
            R.drawable.main_project_category_bg_3,
            R.drawable.main_project_category_bg_4,
            R.drawable.main_project_category_bg_5
    )

    private val mRandom = Random()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(MyApplication.context).inflate(
                R.layout.recycler_view_grid_item_project_tab
                , parent
                , false)
        return ViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = this.list[position]
        val index = this.mRandom.nextInt(bgs.size)
        Logger.i("Current Index:$index")
        holder!!.tvProjectCategoryName.text = item.name
        Glide.with(context).load(bgs[index]).into(holder.ivCover)
        holder.projectTab = item
    }

    class ViewHolder(val context: Context, itemView: View?) : RecyclerView.ViewHolder(itemView) {

        lateinit var projectTab: ProjectTab

        @BindView(R.id.main_project_item_cardview)
        lateinit var llParent: CardView

        @BindView(R.id.main_project_item_ivCategoryCoverImg)
        lateinit var ivCover: ImageView

        @BindView(R.id.main_project_item_tvCategoryName)
        lateinit var tvProjectCategoryName: TextView

        init {
            ButterKnife.bind(this, itemView!!)
            itemView.setOnClickListener {
                ProjectListActivity.show(context, projectTab.name, projectTab.id)
            }
        }
    }
}