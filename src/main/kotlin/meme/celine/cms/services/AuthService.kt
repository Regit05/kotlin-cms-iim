package meme.celine.cms.services

import meme.celine.cms.Model
import meme.celine.cms.model.User

class AuthService(private val model: Model) {
    fun getUser(username: String): User? = model.getUser(username)
}