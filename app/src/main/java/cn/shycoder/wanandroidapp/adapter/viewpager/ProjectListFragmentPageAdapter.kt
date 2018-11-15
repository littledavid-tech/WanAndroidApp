package cn.shycoder.wanandroidapp.adapter.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cn.shycoder.wanandroidapp.model.bean.ProjectTab

/**
 * Created by ShyCoder on 11/15/2018.
 */
class ProjectListFragmentPageAdapter(fm: FragmentManager?, private val projectTabs: List<ProjectTab>)
    : FragmentPagerAdapter(fm) {

    private val mFragmentMap = mutableMapOf<Int, Fragment>()

    override fun getItem(position: Int): Fragment {
//        val tab = this.projectTabs[position]
//        if (!mFragmentMap.containsKey(tab.id)) {
//            mFragmentMap[tab.id] = ProjectListFragment(tab.id)
//        }
//        return mFragmentMap[tab.id]!!
        TODO()
    }

    override fun getCount(): Int {
        return projectTabs.size
    }
}