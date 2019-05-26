package meme.celine.cms.model

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val isAdmin: Boolean
)