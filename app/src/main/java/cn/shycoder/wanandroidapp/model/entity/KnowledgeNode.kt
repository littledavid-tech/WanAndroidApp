package cn.shycoder.wanandroidapp.model.entity

/**
 * Created by ITSoftware on 11/8/2018.
 */

/**
 * 知识体系的 节点
 * */
class KnowledgeNode {

    var children: List<KnowledgeNode>? = null
    var courseId: Int = 0
    var id: Int = 0
    var name: String? = null
    var order: Int = 0
    var parentChapterId: Int = 0
    var userControlSetTop: Boolean = false
    var visible: Int = 0

}
