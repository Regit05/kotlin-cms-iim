<#-- @ftlvariable name="" type="meme.celine.cms.tpl.DetailsContext" -->
<html>
    <head>
        <title>Dashboard</title>
        <link rel="stylesheet" href="../../static/css/main.css">
        <link rel="stylesheet" href="../../static/css/admin.css">
    </head>
    <body>
        <div class="navbar">
            <div class="container" >
                <div class="nav-collapse">
                    <ul>
                        <li><a href="/admin/">Dashboard</a></li>
                        <li><a href="/logout/">Déconnexion</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="container">
            <h1>Détails article</h1>


            <p><b>Identifiant</b> : ${article.id}</p>
            <p><b>Titre</b> : ${article.title}</p>
            <p><b>Contenu</b> : ${article.text}</p>

            <p><b>Commentaires</b> :</p>
            <ul>
                <#list comments as comment>
                    <li>
                        ${comment.id}. ${comment.text}
                        <a href="comment/delete/${article.id}/${comment.id}"><img style={width: "25px";} src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Flat_cross_icon.svg/1024px-Flat_cross_icon.svg.png"/></a>
                    </li>
                </#list>
            </ul>
        </div>
    </body>
</html>