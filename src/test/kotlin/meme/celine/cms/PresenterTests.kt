package meme.celine.cms

import meme.celine.cms.pres.ArticleListPresenterImpl
import meme.celine.cms.pres.ArticlePresenterImpl
import meme.celine.cms.model.Article
import meme.celine.cms.model.Comment
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test

class PresenterTests {

    @Test
    fun testArticleListPresenter() {

        val list = listOf(Article(1, "Un", ""), Article(2, "Deux", ""))

        val model = mock<Model> {
            on { getArticlesList() } doReturn list
        }

        val view = mock<ArticleListPresenter.View>()

        val presenter = ArticleListPresenterImpl(model, view)
        presenter.start()

        verify(model).getArticlesList()
        verify(view).displayArticleList(list)
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testArticlePresenter() {

        val article = Article(1, "Un", "Lorem Ipsum")

        val comments: List<Comment> = listOf(
            Comment(1, 1, "comment1"),
            Comment(2, 1, "comment2"),
            Comment(3, 1, "comment3"),
            Comment(4, 1, "comment4")
        )

        val id = 1

        val model = mock<Model> {
            on { getArticle(id) } doReturn article
        }

        val view = mock<ArticlePresenter.View>()

        val presenter = ArticlePresenterImpl(model, view)
        presenter.start(id)

        verify(model).getArticle(id)
        verify(view).displayArticle(article, comments)
        verify(view, never()).displayNotFound()
        verifyNoMoreInteractions(model, view)
    }

}