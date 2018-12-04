package cn.shycoder.wanandroidapp.view.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText
import cn.shycoder.wanandroidapp.R

/**
 * Created by ShyCoder on 12/3/2018.
 */
class DeletableEditText(context: Context?, attrs: AttributeSet?) : EditText(context, attrs) {

    private var mDeletedDrawable: Drawable? = null

    init {
        mDeletedDrawable = resources.getDrawable(R.drawable.deleteable_edittext_delete_icon, null)
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            /**
             * 当EditText的问呗内容发生改变的时候判断是否需要显示icon
             * */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setDeletedIcon()
            }
        })
    }

    /**
     * 根据文本内容判断是否需要显示icon
     * */
    private fun setDeletedIcon() {
        val right = if (text.isNotEmpty()) mDeletedDrawable else null
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                null,
                right,
                null)
    }

    /**
     * 检测是否需要清除文本
     * */
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event!!.action != MotionEvent.ACTION_DOWN) {
            return true
        }
        val x = event.x
        //清除text
        if (x > this.width - this.mDeletedDrawable!!.intrinsicWidth) {
            this.setText("")
        }
        return true
    }
}