package meme.celine.cms.pres.admin

import meme.celine.cms.Model
import meme.celine.cms.AdminArticlePresenter
import meme.celine.cms.model.Article
import meme.celine.cms.model.Comment

class AdminArticlePresenterImpl(private val model: Model, private val view: AdminArticlePresenter.View) :
    AdminArticlePresenter {
    override fun start(id: Int) {
        val article: Article? = model.getArticle(id)
        val comments: List<Comment> = model.getArticleComments(id)

        if (article != null) {
            view.displayArticle(article, comments)
        } else {
            view.displayNotFound()
        }
    }
}