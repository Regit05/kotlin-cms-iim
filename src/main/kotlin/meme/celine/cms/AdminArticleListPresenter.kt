package meme.celine.cms

import meme.celine.cms.model.Article

interface AdminArticleListPresenter {
    fun start()

    interface View {
        fun displayArticleList(list: List<Article>)
    }
}