package cn.shycoder.wanandroidapp.model.entity

import android.nfc.Tag
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by ITSoftware on 11/7/2018.
 */

class Article() : Parcelable {
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
    var tags: List<cn.shycoder.wanandroidapp.model.entity.Tag>? = null
    var title: String? = null
    var type: Int = 0
    var userId: Int = 0
    var visible: Int = 0
    var zan: Int = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        author = parcel.readString()
        link = parcel.readString()
        title = parcel.readString()
        isCollect = parcel.readByte() == 1.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(author)
        parcel.writeString(link)
        parcel.writeString(title)
        parcel.writeByte(if (isCollect) 1 else 0)

//        parcel.writeString(author)
//        parcel.writeInt(chapterId)
//        parcel.writeString(chapterName)
//        parcel.writeByte(if (isCollect) 1 else 0)
//        parcel.writeInt(courseId)
//        parcel.writeString(desc)
//        parcel.writeString(envelopePic)
//        parcel.writeByte(if (isFresh) 1 else 0)
//        parcel.writeInt(id)
//        parcel.writeString(link)
//        parcel.writeString(niceDate)
//        parcel.writeString(origin)
//        parcel.writeString(projectLink)
//        parcel.writeLong(publishTime)
//        parcel.writeInt(superChapterId)
//        parcel.writeString(superChapterName)
//        parcel.writeTypedList(tags)
//        parcel.writeString(activityTitle)
//        parcel.writeInt(type)
//        parcel.writeInt(userId)
//        parcel.writeInt(visible)
//        parcel.writeInt(zan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}
