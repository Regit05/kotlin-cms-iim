package meme.celine.cms

import meme.celine.cms.model.Article
import meme.celine.cms.model.Comment
import meme.celine.cms.model.User
import meme.celine.cms.tpl.DetailsContext
import meme.celine.cms.tpl.IndexContext
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.*
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.*
import kotlinx.coroutines.launch

class App

data class AuthSession(val user: String)

fun main() {
    val appcomponents = AppComponents("jdbc:mysql://localhost:3306/kotlin-cms?serverTimezone=UTC", "root", "root")

    val server = embeddedServer(Netty, port = 9001) {

        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
        }

        install(Sessions) {
            cookie<AuthSession>("AUTH_SESSION", SessionStorageMemory())
        }

        install(Authentication) {
            form("form-auth") {
                userParamName = "username"
                passwordParamName = "password"
                challenge = FormAuthChallenge.Redirect { "/login" }

                validate { credentials ->
                    val authService = appcomponents.authService
                    val user: User? = authService.getUser(credentials.name)


                        if (user == null) {
                            null
                        } else if(user.isAdmin) {
                            if ((credentials.name == user.username) && (credentials.password == user.password)) {
                                UserIdPrincipal(credentials.name)
                            } else {
                                null
                            }
                        }else{
                            null
                        }

                }
                skipWhen { call -> call.sessions.get<AuthSession>() != null }
            }
        }

        routing {
            static ("static") {
                resources("static")
            }


            get("/article/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()

                val controller: ArticlePresenter = appcomponents.getArticlePresenter(object : ArticlePresenter.View {
                    override fun displayArticle(article: Article, comments: List<Comment>) {
                        val context = DetailsContext(article, comments)
                        launch {
                            call.respond(FreeMarkerContent("details.ftl", context, null))
                        }
                    }

                    override fun displayNotFound() {
                        launch {
                            call.respond(HttpStatusCode.NotFound)
                        }
                    }
                })

                if (id  == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    controller.start(id)
                }
            }

            get ("/") {
                val controller: ArticleListPresenter = appcomponents.getArticleListPresenter(object : ArticleListPresenter.View {
                    override fun displayArticleList(list: List<Article>) {
                        val context = IndexContext(list)
                        launch {
                            call.respond(FreeMarkerContent("index.ftl", context, null))
                        }
                    }
                })

                controller.start()
            }

            get("/login") {
                call.respond(FreeMarkerContent("auth/login.ftl", null, null))
            }

            post("/article/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                val postParameters: Parameters = call.receiveParameters()

                val text: String? = postParameters["text"]

                val controller: ArticlePresenter = appcomponents.getArticlePresenter(object : ArticlePresenter.View {
                    override fun displayArticle(article: Article, comments: List<Comment>) {
                        val context = DetailsContext(article, comments)
                        launch {
                            call.respond(FreeMarkerContent("details.ftl", context, null))
                        }
                    }

                    override fun displayNotFound() {
                        launch {
                            call.respond(HttpStatusCode.NotFound)
                        }
                    }

                })

                if (id == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    controller.postComment(id, text)
                }
            }

            authenticate("form-auth") {
                post("/login") {

                    val principal = call.authentication.principal<UserIdPrincipal>()
                    //println(principal)

                    call.sessions.set(AuthSession(principal!!.name))
                    call.respondRedirect("/admin/")
                }

                route("admin") {
                    get {
                        val controller = appcomponents.getAdminArticleListPresenter(object : AdminArticleListPresenter.View {
                            override fun displayArticleList(list: List<Article>) {
                                val context = IndexContext(list)
                                launch {
                                    call.respond(FreeMarkerContent("admin/adminIndex.ftl", context, null))
                                }
                            }
                        })

                        controller.start()
                    }

                    route("article") {
                        get("/{id}") {
                            val id = call.parameters["id"]?.toIntOrNull()

                            val controller: ArticlePresenter = appcomponents.getArticlePresenter(object : ArticlePresenter.View {
                                override fun displayArticle(article: Article, comments: List<Comment>) {
                                    val context = DetailsContext(article, comments)
                                    launch {
                                        call.respond(FreeMarkerContent("admin/adminDetails.ftl", context, null))
                                    }
                                }
                                override fun displayNotFound() {
                                    launch {
                                        call.respond(HttpStatusCode.NotFound)
                                    }
                                }
                            })

                            if (id  == null) {
                                call.respond(HttpStatusCode.NotFound)
                            } else {
                                controller.start(id)
                            }
                        }

                        get("delete/{id}") {
                            val id = call.parameters["id"]?.toIntOrNull()

                            val controller: AdminCallsPresenter = appcomponents.getAdminCallsPresenter(object : AdminCallsPresenter.View {
                                override fun redirect() {
                                    launch {
                                        call.respondRedirect("/admin/")
                                    }
                                }
                                override fun displayNotFound() {
                                    launch {
                                        call.respond(HttpStatusCode.NotFound)
                                    }
                                }
                            })

                            if (id == null) {
                                call.respond(HttpStatusCode.NotFound)
                            } else {
                                controller.deleteArticle(id)
                            }
                        }

                        post("add") {
                            val postParameters: Parameters = call.receiveParameters()

                            val title: String? = postParameters["title"]
                            val text: String? = postParameters["text"]

                            val controller: AdminCallsPresenter = appcomponents.getAdminCallsPresenter(object : AdminCallsPresenter.View {
                                override fun redirect() {
                                    launch {
                                        call.respondRedirect("/admin/")
                                    }
                                }

                                override fun displayNotFound() {
                                    launch {
                                        call.respond(HttpStatusCode.NotFound)
                                    }
                                }
                            })

                            if (title == null || text == null) {
                                call.respond(HttpStatusCode.NotFound)
                            } else {
                                controller.addArticle(title, text)
                            }
                        }

                        route("comment") {
                            get ("delete/{idArticle}/{id}") {
                                val id = call.parameters["id"]?.toIntOrNull()
                                val idArticle = call.parameters["idArticle"]?.toIntOrNull()

                                val controller: AdminCallsPresenter = appcomponents.getAdminCallsPresenter(object : AdminCallsPresenter.View {
                                    override fun redirect() {
                                        launch {
                                            call.respondRedirect("/admin/article/${idArticle}")
                                        }
                                    }

                                    override fun displayNotFound() {
                                        launch {
                                            call.respond(HttpStatusCode.NotFound)
                                        }
                                    }
                                })

                                if (id == null) {
                                    call.respond(HttpStatusCode.NotFound)
                                } else {
                                    //val  = controller.getComment(id)
                                    controller.deleteComment(id)
                                }
                            }
                        }
                    }
                }

                get("/logout") {
                    call.sessions.clear<AuthSession>()
                    call.respondRedirect("/")
                }
            }
        }
    }

    server.start(wait = true)
}