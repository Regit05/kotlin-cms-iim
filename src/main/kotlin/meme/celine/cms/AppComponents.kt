package meme.celine.cms

import meme.celine.cms.pres.ArticleListPresenterImpl
import meme.celine.cms.pres.ArticlePresenterImpl
import meme.celine.cms.pres.admin.AdminArticleListPresenterImpl
import meme.celine.cms.pres.admin.AdminCallsPresenterImpl
import meme.celine.cms.services.AuthService

class AppComponents(mysqlUrl: String, mysqlUser: String, mysqlPassword: String) {
    private val pool = ConnectionPool(mysqlUrl, mysqlUser, mysqlPassword)

    private val model = MysqlModel(getPool())

    private fun getPool(): ConnectionPool = pool

    private fun getModel(): Model = model

    fun getArticleListPresenter(view: ArticleListPresenter.View): ArticleListPresenter = ArticleListPresenterImpl(getModel(), view)

    fun getArticlePresenter(view: ArticlePresenter.View): ArticlePresenter = ArticlePresenterImpl(getModel(), view)

    fun getAdminArticleListPresenter(view: AdminArticleListPresenter.View): AdminArticleListPresenter = AdminArticleListPresenterImpl(getModel(), view)

    fun getAdminCallsPresenter(view: AdminCallsPresenter.View): AdminCallsPresenter = AdminCallsPresenterImpl(getModel(), view)

    val authService = AuthService(getModel())
}