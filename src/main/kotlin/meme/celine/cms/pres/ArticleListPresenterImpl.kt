package meme.celine.cms.pres

import meme.celine.cms.ArticleListPresenter
import meme.celine.cms.Model

class ArticleListPresenterImpl(private val model: Model, private val view: ArticleListPresenter.View) : ArticleListPresenter {
    override fun start() {
        val list = model.getArticlesList()
        view.displayArticleList(list)
    }
}