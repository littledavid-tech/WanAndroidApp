package cn.shycoder.wanandroidapp.model.entity

import android.nfc.Tag
import android.os.Parcel
import android.os.Parcelable

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
    var tags: List<Tag>? = null
    var title: String? = null
    var type: Int = 0
    var userId: Int = 0
    var visible: Int = 0
    var zan: Int = 0

    constructor(parcel: Parcel) : this() {
        apkLink = parcel.readString()
        author = parcel.readString()
        chapterId = parcel.readInt()
        chapterName = parcel.readString()
        isCollect = parcel.readByte() != 0.toByte()
        courseId = parcel.readInt()
        desc = parcel.readString()
        envelopePic = parcel.readString()
        isFresh = parcel.readByte() != 0.toByte()
        id = parcel.readInt()
        link = parcel.readString()
        niceDate = parcel.readString()
        origin = parcel.readString()
        projectLink = parcel.readString()
        publishTime = parcel.readLong()
        superChapterId = parcel.readInt()
        superChapterName = parcel.readString()
        tags = parcel.createTypedArrayList(Tag.CREATOR)
        title = parcel.readString()
        type = parcel.readInt()
        userId = parcel.readInt()
        visible = parcel.readInt()
        zan = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(apkLink)
        parcel.writeString(author)
        parcel.writeInt(chapterId)
        parcel.writeString(chapterName)
        parcel.writeByte(if (isCollect) 1 else 0)
        parcel.writeInt(courseId)
        parcel.writeString(desc)
        parcel.writeString(envelopePic)
        parcel.writeByte(if (isFresh) 1 else 0)
        parcel.writeInt(id)
        parcel.writeString(link)
        parcel.writeString(niceDate)
        parcel.writeString(origin)
        parcel.writeString(projectLink)
        parcel.writeLong(publishTime)
        parcel.writeInt(superChapterId)
        parcel.writeString(superChapterName)
        parcel.writeTypedList(tags)
        parcel.writeString(title)
        parcel.writeInt(type)
        parcel.writeInt(userId)
        parcel.writeInt(visible)
        parcel.writeInt(zan)
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
