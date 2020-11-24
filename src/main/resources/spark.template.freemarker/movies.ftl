<html>
    <head>
        <title>Movies Library</title>
        <link rel="stylesheet" href="/style.css">
    </head>
    <body>
        <header>Movie Library</header>
        <#list movies as movie>
            <section>
                <p>Movie: ${movie.title}</p>
                <p>Year: ${movie.year}</p>
                <p>Director: ${movie.director}</p>
                <img style="width: 100px;" src="${movie.image}"/> </br>
                <a href="/movies/${movie.title}">more...</a>
                <p> ---------------------- </p>
            </section>
        </#list>
        <footer> I am learning Java and it's not easy :( </footer>
    </body>
</html>