package meme.celine.cms

import meme.celine.cms.model.Article
import meme.celine.cms.model.Comment
import meme.celine.cms.model.User

interface Model {
    fun getArticlesList(): List<Article>

    fun getArticle(id: Int): Article?

    fun deleteArticle(id: Int)

    fun deleteArticleComments(id: Int)

    fun deleteComment(id: Int)

    fun getArticleComments(id: Int): List<Comment>

    //fun getComment(id: Int): Comment?

    fun addArticle(title: String, text: String)

    fun postComment(id: Int, text: String)

    fun getUser(username: String): User?
}