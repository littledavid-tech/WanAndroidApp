package cn.shycoder.wanandroidapp.view

import android.view.View
import android.widget.ImageView
import cn.shycoder.wanandroidapp.model.entity.HomeBanner
import cn.shycoder.wanandroidapp.MyApplication
import com.bigkoo.convenientbanner.holder.Holder
import com.bumptech.glide.Glide


/**
 * ConvenientBanner 的 Item 的 Holder
 * 在这里完成加载图片的操作
 * */
class NetworkImageHolderView(itemView: View?) : Holder<HomeBanner>(itemView) {
    lateinit var imageView: ImageView

    override fun updateUI(data: HomeBanner?) {
        Glide.with(MyApplication.context).load(data!!.imagePath).into(imageView)
    }

    override fun initView(itemView: View?) {
        imageView = itemView as ImageView
    }
}