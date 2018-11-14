package cn.shycoder.wanandroidapp.adapter.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by ITSoftware on 11/7/2018.
 */
abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(val context: Context, val list: MutableList<T>)
    : RecyclerView.Adapter<VH>() {

    abstract override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH

    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * 向集合的末尾追加元素
     * */
    fun addItem(t: T) {
        list.add(t)
        notifyItemChanged(list.size - 1)
    }

    fun addList(datas: List<T>) {
        list.addAll(datas)
        notifyDataSetChanged()
    }
}