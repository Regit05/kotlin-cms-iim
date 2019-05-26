package meme.celine.cms.tpl

import meme.celine.cms.model.Article

data class IndexContext(
    val articles: List<Article>
)