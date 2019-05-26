package meme.celine.cms

import meme.celine.cms.model.Article
import meme.celine.cms.model.Comment

interface ArticlePresenter {
    fun start(id: Int)

    fun postComment(id: Int, text: String?)

    interface View {
        fun displayArticle(article: Article, comments: List<Comment>)

        fun displayNotFound()
    }
}