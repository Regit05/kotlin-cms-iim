package meme.celine.cms.pres.admin

import meme.celine.cms.Model
import meme.celine.cms.AdminArticleListPresenter

class AdminArticleListPresenterImpl(private val model: Model, private val view: AdminArticleListPresenter.View) :
    AdminArticleListPresenter {
    override fun start() {
        val list = model.getArticlesList()
        view.displayArticleList(list)
    }
}