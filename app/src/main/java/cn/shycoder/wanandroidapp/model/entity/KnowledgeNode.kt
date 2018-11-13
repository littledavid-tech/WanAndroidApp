package cn.shycoder.wanandroidapp.model.entity

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by ITSoftware on 11/8/2018.
 */

/**
 * 知识体系的 节点
 * */
class KnowledgeNode() : Parcelable {

    var children: List<KnowledgeNode>? = null
    var courseId: Int = 0
    var id: Int = 0
    var name: String? = null
    var order: Int = 0
    var parentChapterId: Int = 0
    var userControlSetTop: Boolean = false
    var visible: Int = 0

    constructor(parcel: Parcel) : this() {
        courseId = parcel.readInt()
        id = parcel.readInt()
        name = parcel.readString()
        order = parcel.readInt()
        parentChapterId = parcel.readInt()
        userControlSetTop = parcel.readByte() != 0.toByte()
        visible = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(courseId)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(order)
        parcel.writeInt(parentChapterId)
        parcel.writeByte(if (userControlSetTop) 1 else 0)
        parcel.writeInt(visible)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<KnowledgeNode> {
        override fun createFromParcel(parcel: Parcel): KnowledgeNode {
            return KnowledgeNode(parcel)
        }

        override fun newArray(size: Int): Array<KnowledgeNode?> {
            return arrayOfNulls(size)
        }
    }

}
