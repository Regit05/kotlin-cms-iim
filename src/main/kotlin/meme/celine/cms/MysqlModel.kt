package meme.celine.cms

import meme.celine.cms.model.Article
import meme.celine.cms.model.Comment
import meme.celine.cms.model.User

class MysqlModel(private val pool: ConnectionPool) : Model {

    override fun getArticlesList(): List<Article> {
        val articles = ArrayList<Article>()

        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM articles").use {stmt ->
                stmt.executeQuery().use {results ->
                    while (results.next()) {
                        articles += Article(
                            results.getInt("id"),
                            results.getString("title"),
                            results.getString("text")
                        )
                    }

                    //val context = IndexContext(articles)
                    //call.respond(FreeMarkerContent("index.ftl", context, "e"))
                }
            }
        }

        return articles
    }

    override fun getArticle(id: Int): Article? {
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM articles WHERE id = ?").use {stmt ->
                stmt.setInt(1, id)
                stmt.executeQuery().use {result ->
                    if (result.next()) {
                        /*val article = Article(
                            result.getInt("id"),
                            result.getString("title"),
                            result.getString("text")
                        )*/

                        return Article(
                            result.getInt("id"),
                            result.getString("title"),
                            result.getString("text")
                        )
                        //call.respond(FreeMarkerContent("detailsArticle.ftl", article, "e"))
                    }
                }
            }
        }
        return null
    }

    override fun addArticle(title: String, text: String) {
        pool.useConnection { connection ->
            connection.prepareStatement("INSERT INTO articles (title, text) VALUES (?, ?)").use {stmt ->
                stmt.setString(1, title)
                stmt.setString(2, text)
                stmt.execute()
            }
        }
    }

    override fun deleteArticle(id: Int) {
        pool.useConnection { connection ->
            connection.prepareStatement("DELETE FROM articles WHERE id = ?").use {stmt ->
                stmt.setInt(1, id)
                stmt.execute()
            }
        }
    }

    override fun postComment(id: Int, text: String) {
        pool.useConnection { connection ->
            connection.prepareStatement("INSERT INTO comments (article_id, text) VALUES (?, ?)").use {stmt ->
                stmt.setInt(1, id)
                stmt.setString(2, text)
                stmt.executeUpdate()
            }
        }
    }

    override fun deleteArticleComments(id: Int) {
        pool.useConnection { connection ->
            connection.prepareStatement("DELETE FROM comments WHERE article_id = ?").use {stmt ->
                stmt.setInt(1, id)
                stmt.execute()
            }
        }
    }

    override fun deleteComment(id: Int) {
        pool.useConnection { connection ->
            connection.prepareStatement("DELETE FROM comments WHERE id = ?").use {stmt ->
                stmt.setInt(1, id)
                stmt.execute()
            }
        }
    }

    override fun getArticleComments(id: Int): List<Comment> {
        val comments = ArrayList<Comment>()

        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM comments WHERE article_id = ?").use {stmt ->
                stmt.setInt(1, id)
                stmt.executeQuery().use {results ->
                    while (results.next()) {
                        comments += Comment (
                            results.getInt("id"),
                            results.getInt("article_id"),
                            results.getString("text")
                        )
                    }
                }
            }
        }

        return comments
    }

    /*override fun getComment(id: Int): Comment? {
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM comments WHERE id = ?").use {stmt ->
                stmt.setInt(1, id)
                stmt.executeQuery().use {result ->
                    if (result.next()) {
                        return Comment(
                            result.getInt("id"),
                            result.getInt("idArticle"),
                            result.getString("text")
                        )
                    }
                }
            }
        }
        return null
    }*/


    override fun getUser(username: String): User? {
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM users WHERE username = ? AND isAdmin = 1").use {stmt ->
                stmt.setString(1, username)
                stmt.executeQuery().use {result ->
                    if (result.next()) {
                        return User(
                            result.getInt("id"),
                            result.getString("username"),
                            result.getString("password"),
                            result.getBoolean("isAdmin")
                        )
                    }
                }
            }
        }
        return null
    }
}