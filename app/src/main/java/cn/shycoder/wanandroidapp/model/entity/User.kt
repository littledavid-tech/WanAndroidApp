package cn.shycoder.wanandroidapp.model.entity


class User {
    var chapterTops: List<String>? = null
    var collectIds: MutableList<Int>? = null
    var email: String = ""
    var icon: String = ""
    var id: Int = -1
    var password: String = ""
    var token: String = ""
    var type: Int = 0
    var username: String = ""

    /**
     * 判断指定的文章是否已经是被收藏
     * */
    fun isArticleCollected(articleId: Int): Boolean {
        if (collectIds == null) {
            return false
        }
        return collectIds!!.contains(articleId)
    }

    /**
     * 添加收藏的文章
     * @param articleId 文章id
     * */
    fun addCollectedArticle(articleId: Int) {
        if (collectIds == null) {
            collectIds = mutableListOf(articleId)
        } else {
            collectIds!!.add(articleId)
        }

    }

    /**
     * 添加收藏的文章
     * @param articleId 文章id
     * */
    fun removeColletedArticle(articleId: Int) {
        if (collectIds == null) {
            return
        }
        collectIds!!.remove(articleId)
    }


}