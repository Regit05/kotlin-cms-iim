package meme.celine.cms.pres.admin

import meme.celine.cms.Model
import meme.celine.cms.AdminCallsPresenter

class AdminCallsPresenterImpl(private val model: Model, private val view: AdminCallsPresenter.View) :
    AdminCallsPresenter {
    override fun addArticle(title: String, text: String) {
        model.addArticle(title, text)
        view.redirect()
    }

    override fun deleteArticle(id: Int) {
        model.deleteArticleComments(id)
        model.deleteArticle(id)
        view.redirect()
    }

    override fun deleteComment(id: Int) {
        model.deleteComment(id)
        view.redirect()
    }

    /*override fun getComment(id: Int) {

        val comment: Comment? = model.getComment(id)

        if (comment != null) {
            view.displayComment(comment)
        } else {
            view.displayNotFound()
        }


        view.redirect()
    }*/
}