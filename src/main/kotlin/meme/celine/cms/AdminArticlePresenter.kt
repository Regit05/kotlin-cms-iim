package meme.celine.cms

import meme.celine.cms.model.Article
import meme.celine.cms.model.Comment

interface AdminArticlePresenter {
    fun start(id: Int)

    interface View {
        fun displayArticle(article: Article, comments: List<Comment>)

        fun displayNotFound()
    }
}