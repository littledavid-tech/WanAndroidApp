package cn.shycoder.wanandroidapp.model.entity

import android.nfc.Tag

/**
 * Created by ITSoftware on 11/7/2018.
 */

class Article {
    var apkLink: String? = null
    var author: String? = null
    var chapterId: Int = 0
    var chapterName: String? = null
    var isCollect: Boolean = false
    var courseId: Int = 0
    var desc: String? = null
    var envelopePic: String? = null
    var isFresh: Boolean = false
    var id: Int = 0
    var link: String? = null
    var niceDate: String? = null
    var origin: String? = null
    var projectLink: String? = null
    var publishTime: Long = 0
    var superChapterId: Int = 0
    var superChapterName: String? = null
    var tags: List<Tag>? = null
    var title: String? = null
    var type: Int = 0
    var userId: Int = 0
    var visible: Int = 0
    var zan: Int = 0
}
