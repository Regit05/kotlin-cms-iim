package meme.celine.cms

import meme.celine.cms.model.Article

interface ArticleListPresenter {
    fun start()

    interface View {
        fun displayArticleList(list: List<Article>)
    }
}