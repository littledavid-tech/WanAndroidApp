package cn.shycoder.wanandroidapp.view.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.SPKeyConst
import cn.shycoder.wanandroidapp.utils.DateTimeUtils
import cn.shycoder.wanandroidapp.MyApplication
import cn.shycoder.wanandroidapp.utils.NetworkUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.net.URL

class WelcomeActivity
    : AppCompatActivity()
        , RequestListener<Drawable> {


    @BindView(R.id.welcome_ivBingWallPaper)
    lateinit var ivWallPaper: ImageView

    private var mUnbinder: Unbinder? = null

    private var mDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.welcome_activity)
        this.mUnbinder = ButterKnife.bind(this)
        loadWelcomeImage()
    }

    /**
     * 回收一些资源
     * */
    override fun onDestroy() {
        super.onDestroy()
        this.mDisposable?.dispose()
        this.mUnbinder?.unbind()
    }

    /**
     * 加载Bing美图
     * */
    private fun loadWelcomeImage() {

        if (NetworkUtils.isWiFi()) {
            io.reactivex.Observable
                    .create<String> {
                        it.onNext(getBingImageUrlStr())
                        it.onComplete()
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<String> {
                        override fun onComplete() {

                        }

                        override fun onSubscribe(d: Disposable) {
                            this@WelcomeActivity.mDisposable = d
                        }

                        override fun onNext(t: String) {
                            Glide.with(this@WelcomeActivity)
                                    .load(t)
                                    .listener(this@WelcomeActivity)
                                    .into(ivWallPaper)
                            MyApplication.putStringToSP(DateTimeUtils.getCurrentDate() +
                                    SPKeyConst.sp_key_bing_img_address, t)
                        }

                        override fun onError(e: Throwable) {
                            Logger.e("Load bing image error! ")
                            e.printStackTrace()
                        }

                    })

        } else {
            loadDefaultImg()
        }
    }

    /**
     * 设置沉浸模式
     * */
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    /***
     * 加载Bing每日美图的Url
     * @return 必应美图的Url
     * */
    private fun getBingImageUrlStr(): String {
        try {
            //先从SP中加载图片URL
            var fullUrl = MyApplication.getStringFromSP(DateTimeUtils.getCurrentDate(), "")
            //SP中没有图片的URL，从API中拉取
            if (fullUrl.isEmpty()) {
                val content = URL("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1").readText()
                val jsonObject = JSONObject(content)
                val jsonArray = jsonObject.getJSONArray("images")
                val urlStr = jsonArray.getJSONObject(0).getString("url")
                fullUrl = "https://cn.bing.com/$urlStr"
            }
            Logger.i("Bing Image Url:$fullUrl")

            return fullUrl
        } catch (ex: Exception) {
            throw  ex
        }
    }

    /**
     * 当没有WiFi的时候，下载一个默认的图片
     * */
    private fun loadDefaultImg() {
        Glide.with(this)
                .load(R.drawable.default_welcome_wallpaper)
                .listener(this)
                .into(ivWallPaper)
    }

    /**
     * 启动一个延时任务2秒之后跳转到主界面，并结束当前的Activity
     * */
    private fun beginDelayTask() {
        Handler().postDelayed({
            MainActivity.show(this)
            this.mDisposable?.dispose()
            this.finish()
        }, 2000)
    }

    override fun onBackPressed() {

    }

    /**
     * Glide 加载图片失败
     * */
    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?,
                              isFirstResource: Boolean): Boolean {
        Logger.d("Failed to load image from network")
        loadDefaultImg()
        beginDelayTask()
        return true
    }

    /**
     * Glide 加载图片成功
     * */
    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                 dataSource: DataSource?, isFirstResource: Boolean): Boolean {
        Logger.d("Load image from network successfully!")
        beginDelayTask()
        return false
    }

}
