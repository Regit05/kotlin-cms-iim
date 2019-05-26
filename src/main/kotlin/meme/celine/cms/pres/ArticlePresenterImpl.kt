package meme.celine.cms.pres

import meme.celine.cms.ArticlePresenter
import meme.celine.cms.Model
import meme.celine.cms.model.Article
import meme.celine.cms.model.Comment

class ArticlePresenterImpl(private val model: Model, private val view: ArticlePresenter.View) : ArticlePresenter {

    override fun start(id: Int) {
        val article: Article? = model.getArticle(id)
        val comments: List<Comment> = model.getArticleComments(id)

        if (article != null) {
            view.displayArticle(article, comments)
        } else {
            view.displayNotFound()
        }
    }

    override fun postComment(id: Int, text: String?) {
        if ((text != null)) {
            model.postComment(id, text)
            start(id)
        } else {
            null
        }
    }
}