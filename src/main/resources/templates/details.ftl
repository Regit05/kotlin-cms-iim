<#-- @ftlvariable name="" type="meme.celine.cms.tpl.DetailsContext" -->
<html>
    <head>
        <title>Details Article</title>
        <link rel="stylesheet" href="../../static/css/main.css">
        <link rel="stylesheet" href="../../static/css/details.css">
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
        <div class="divdetailsarticle">
            <h1> ${article.title} (${article.id})</h1>
            <p>${article.text}</p>


            <h2> Commentaires </h2>
            <form class="formpostcomment" method="post">
                <textarea type="text" name="text" placeholder="Your comment..." required></textarea>
                <button>Post</button>
            </form>
            <ul>
                <#list comments as comment>
                    <li>
                        ${comment.text}
                    </li>
                </#list>
            </ul>

        </div>
    </body>
</html>