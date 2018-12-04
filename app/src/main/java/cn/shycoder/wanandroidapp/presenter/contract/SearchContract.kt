package cn.shycoder.wanandroidapp.presenter.contract

import android.content.Context
import cn.shycoder.wanandroidapp.model.entity.Hotkey

/**
 * Created by ShyCoder on 12/3/2018.
 */
interface SearchContract {

    interface View : BaseContract.View {
        fun loadedHotkey(list: List<Hotkey>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadHotkey()

        fun disposeSearchEvent(context: Context, key: String)
    }
}