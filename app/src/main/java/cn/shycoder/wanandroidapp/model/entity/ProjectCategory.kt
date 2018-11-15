package cn.shycoder.wanandroidapp.model.entity


/**
 * 项目分类的实体类
 * */
class ProjectCategory {
    var children: List<ProjectCategory>? = null
    var courseId: Int? = null
    var id: Int? = null
    var name: String? = null
    var order: Int? = null
    var parentChapterId: Int? = null
    var userControlSetTop: Boolean = false
    var visible: Int? = null
}