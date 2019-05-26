<#-- @ftlvariable name="" type="meme.celine.cms.tpl.IndexContext" -->
<html>
    <head>
        <title>Blog</title>
        <link rel="stylesheet" href="./static/css/main.css">
    </head>
    <body>
        <div class="navbar">
            <div class="container" >
                <div class="nav-collapse">
                    <ul>
                        <li><a href="/">List of articles</a></li>
                    </ul>
                    <ul>
                        <li><a href="/admin/">Login</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="container">
            <h1><img src="https://cdn.pixabay.com/photo/2014/04/03/10/44/newspaper-311272_960_720.png"/> Articles</h1>

            <ul>
                <#list articles as article>
                    <li>
                        <a href="article/${article.id}">${article.title}</a>
                    </li>
                </#list>
            </ul>
        </div>
    </body>
</html>