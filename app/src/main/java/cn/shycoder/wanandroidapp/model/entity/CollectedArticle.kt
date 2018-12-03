package cn.shycoder.wanandroidapp.model.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ShyCoder on 12/3/2018.
 */

class CollectedArticle() : Parcelable {

    var author: String? = null
    var chapterId: Int = 0
    var chapterName: String? = null
    var courseId: Int = 0
    var desc: String? = null
    var envelopePic: String? = null
    var id: Int = 0
    var link: String? = null
    var niceDate: String? = null
    var origin: String? = null
    var originId: Int = -1
    var publishTime: Long = 0
    var title: String? = null
    var userId: Int = 0
    var visible: Int = 0
    var zan: Int = 0

    constructor(parcel: Parcel) : this() {
        author = parcel.readString()
        chapterId = parcel.readInt()
        chapterName = parcel.readString()
        courseId = parcel.readInt()
        desc = parcel.readString()
        envelopePic = parcel.readString()
        id = parcel.readInt()
        link = parcel.readString()
        niceDate = parcel.readString()
        origin = parcel.readString()
        originId = parcel.readInt()
        publishTime = parcel.readLong()
        title = parcel.readString()
        userId = parcel.readInt()
        visible = parcel.readInt()
        zan = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeInt(chapterId)
        parcel.writeString(chapterName)
        parcel.writeInt(courseId)
        parcel.writeString(desc)
        parcel.writeString(envelopePic)
        parcel.writeInt(id)
        parcel.writeString(link)
        parcel.writeString(niceDate)
        parcel.writeString(origin)
        parcel.writeInt(originId)
        parcel.writeLong(publishTime)
        parcel.writeString(title)
        parcel.writeInt(userId)
        parcel.writeInt(visible)
        parcel.writeInt(zan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CollectedArticle> {
        override fun createFromParcel(parcel: Parcel): CollectedArticle {
            return CollectedArticle(parcel)
        }

        override fun newArray(size: Int): Array<CollectedArticle?> {
            return arrayOfNulls(size)
        }
    }
}
