package meme.celine.cms.tpl

import meme.celine.cms.model.Article
import meme.celine.cms.model.Comment

data class DetailsContext(
    val article: Article,
    val comments: List<Comment>
)