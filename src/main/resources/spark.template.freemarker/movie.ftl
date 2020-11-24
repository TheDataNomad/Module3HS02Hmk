<html>
    <head>
        <title>Movie ${movie.title}</title>
        <link rel="stylesheet" href="/style.css">
    </head>
    <body>
        <header>Movie "${movie.title}"</header>

        <section>
            <p>Rating: ${movie.rating}</p>
            <p>Year: ${movie.year}</p>
            <p>Director: ${movie.director}</p>
            <p>Runtime: ${movie.runtime}</p>
            <#assign gen = movie.genres>
            <p>Genre: ${gen?join(", ")}</p>
            <#assign act = movie.actors>
            <p>Genre: ${act?join(", ")}</p>
            <p>Poster:</p>
            <img style="width: 100px;" src="${movie.image}"/> </br>
            <a href="/movies">go back</a>
        </section>
        </br>
        <footer> I am learning Java and it's not easy :( </footer>
    </body>
</html>