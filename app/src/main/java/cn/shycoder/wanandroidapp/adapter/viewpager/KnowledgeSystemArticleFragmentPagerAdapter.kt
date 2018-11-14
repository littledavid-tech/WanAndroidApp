package cn.shycoder.wanandroidapp.adapter.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cn.shycoder.wanandroidapp.model.bean.KnowledgeSystemTab
import cn.shycoder.wanandroidapp.view.fragment.KnowledgeSystemArticleFragment
import com.orhanobut.logger.Logger

class KnowledgeSystemArticleFragmentPagerAdapter
(fm: FragmentManager?, private val tabs: List<KnowledgeSystemTab>)
    : FragmentPagerAdapter(fm) {

    private val mFragmentMap = mutableMapOf<Int, Fragment>()

    override fun getItem(position: Int): Fragment {
        Logger.i("getItem")

        if (!mFragmentMap.containsKey(position)) {
            mFragmentMap[position] = KnowledgeSystemArticleFragment(tabs[position])
        }
        return mFragmentMap[position]!!

//        return KnowledgeSystemArticleFragment(this.tabs[position])
    }

    override fun getCount(): Int {
        return tabs.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabs[position].title
    }
}