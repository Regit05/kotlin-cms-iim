<#-- @ftlvariable name="" type="meme.celine.cms.tpl.IndexContext" -->
<html>
    <head>
        <title>Dashboard</title>
        <link rel="stylesheet" href="../static/css/main.css">
        <link rel="stylesheet" href="../static/css/admin.css">
    </head>
    <body>
        <div class="navbar">
            <div class="container" >
                <div class="nav-collapse">
                    <ul>
                        <li><a href="/logout/">Déconnexion</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="container">
            <h1>Dashboard</h1>
            <div>
                <form action="article/add" method="post">
                    <label>New article:</label>
                    <input type="text" name="title" placeholder="Title" required>
                    <textarea type="text" name="text" placeholder="Text"></textarea>
                    <button>Create article</button>
                </form>
                <ul>
                    <p>Liste des articles</p>
                    <#list articles as article>
                        <li>
                            <a href="article/${article.id}">${article.title}</a>
                            <a href="article/delete/${article.id}"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Flat_cross_icon.svg/1024px-Flat_cross_icon.svg.png"/></a>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </body>
</html>