package space.harbour.java.hm4;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
////////////
import java.util.Arrays;
/////////////////////////////
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;



public class Movies implements Jsonable {
    String title;
    int year;
    String released;
    int runtime;
    String[] genres;
    Director director;
    Writer[] writers;
    Actor[] actors;
    String plot;
    String[] languages;
    String[] countries;
    String awards;
    String poster;
    Ratings[] ratings;

    @Override
    public String toString() {
        return "Movies{"
                + "title='" + title + '\''
                + ", year=" + year
                + ", released='" + released + '\''
                + ", runtime=" + runtime
                + ", genres=" + Arrays.toString(genres)
                + ", director=" + director
                + ", writer=" + Arrays.toString(writers)
                + ", actors=" + Arrays.toString(actors)
                + ", plot='" + plot + '\''
                + ", languages=" + Arrays.toString(languages)
                + ", countries=" + Arrays.toString(countries)
                + ", awards='" + awards + '\''
                + ", poster='" + poster + '\''
                + ", ratings=" + Arrays.toString(ratings) + '}';
    }

    public static class Director {
        String name;

        @Override
        public String toString() {
            return "Director{"
                    + "name='" + name + '\'' + '}';
        }
    }

    public static class Writer {
        String name;
        String type;

        @Override
        public String toString() {
            return "Writer{"
                    + "name='" + name + '\''
                    + ", type='" + type + '\'' + '}';
        }
    }

    public static class Actor {
        String name;
        String as;

        @Override
        public String toString() {
            return "Actor{"
                    + "name='" + name + '\''
                    + ", as='" + as + '\'' + '}';
        }
    }

    public static class Ratings {
        String source;
        String value;
        int votes;

        @Override
        public String toString() {
            return "Ratings{"
                    + "source='" + source + '\''
                    + ", value='" + value + '\''
                    + ", votes=" + votes + '}';
        }
    }


    public static void writeObjectToFile(JsonObject obj, String fileName) throws IOException {
        FileOutputStream fout = new FileOutputStream(fileName);
        JsonWriter writer = Json.createWriter(fout);
        writer.writeObject(obj);
        writer.close();
    }

    public void readFromJsonFile(String filename) throws FileNotFoundException {
        FileInputStream in = new FileInputStream(filename);
        BufferedInputStream bin = new BufferedInputStream(in);

        JsonReader reader = Json.createReader(bin);
        JsonObject jsonObject = reader.readObject();
        this.title = jsonObject.getString("Title");
        this.year = jsonObject.getInt("Year");
        this.released = jsonObject.getString("Released");
        this.runtime = jsonObject.getInt("Runtime");

        JsonArray genresArray = jsonObject.getJsonArray("Genres");
        this.genres = new String[genresArray.size()];
        for (int i = 0; i < this.genres.length; i++) {
            this.genres[i] = genresArray.get(i).toString();
        }

        JsonObject directorObject = jsonObject.getJsonObject("Director");
        this.director = new Director();
        this.director.name  = directorObject.getString("Name");

        JsonArray writerArray = jsonObject.getJsonArray("Writers");
        this.writers = new Writer[writerArray.size()];
        for (int i = 0; i < this.writers.length; i++) {
            this.writers[i] = new Writer();
            this.writers[i].name = writerArray.getJsonObject(i).getString("Name");
            this.writers[i].type = writerArray.getJsonObject(i).getString("Type");
        }

        JsonArray actorsArray = jsonObject.getJsonArray("Actors");
        this.actors = new Actor[actorsArray.size()];
        for (int i = 0; i < this.actors.length; i++) {
            this.actors[i] = new Actor();
            this.actors[i].name = actorsArray.getJsonObject(i).getString("Name");
            this.actors[i].as = actorsArray.getJsonObject(i).getString("As");
        }

        this.plot = jsonObject.getString("Plot");

        JsonArray languagesArray = jsonObject.getJsonArray("Languages");
        this.languages = new String[languagesArray.size()];
        for (int i = 0; i < this.languages.length; i++) {
            this.languages[i] = languagesArray.get(i).toString();
        }

        JsonArray countriesArray = jsonObject.getJsonArray("Countries");
        this.countries = new String[countriesArray.size()];
        for (int i = 0; i < this.countries.length; i++) {
            this.countries[i] = countriesArray.get(i).toString();
        }

        this.awards = jsonObject.getString("Awards");
        this.poster = jsonObject.getString("Poster");

        JsonArray ratingsArray = jsonObject.getJsonArray("Ratings");
        this.ratings = new Ratings[ratingsArray.size()];
        for (int i = 0; i < this.ratings.length; i++) {
            this.ratings[i] = new Ratings();
            this.ratings[i].source = ratingsArray.getJsonObject(i).getString("Source");
            this.ratings[i].value = ratingsArray.getJsonObject(i).getString("Value");
            this.ratings[i].votes = ratingsArray.getJsonObject(i).getInt("Votes", 0);
        }
    }

    @Override
    public JsonObject toJsonObject() {

        JsonArrayBuilder arrayGenres = Json.createArrayBuilder();
        for (String s : genres) {
            arrayGenres.add(s);
        }


        JsonObjectBuilder objectDirector = Json.createObjectBuilder();
        objectDirector.add("Name", director.name);

        JsonArrayBuilder arrayWriters = Json.createArrayBuilder();
        JsonObjectBuilder objectWriter = Json.createObjectBuilder();
        for (Writer s : writers) {
            objectWriter.add("Name", s.name);
            objectWriter.add("Type", s.type);
            JsonObject jsonWriter = objectWriter.build();

            arrayWriters.add(jsonWriter);
        }

        JsonArrayBuilder arrayActors = Json.createArrayBuilder();
        JsonObjectBuilder objectActor = Json.createObjectBuilder();
        for (Actor s : actors) {
            objectActor.add("Name", s.name);
            objectActor.add("As", s.as);
            JsonObject jsonActor = objectActor.build();

            arrayActors.add(jsonActor);
        }


        JsonArrayBuilder arrayLanguages = Json.createArrayBuilder();
        for (String s : languages) {
            arrayLanguages.add(s);
        }

        JsonArrayBuilder arrayCountries = Json.createArrayBuilder();
        for (String s : languages) {
            arrayCountries.add(s);
        }

        JsonArrayBuilder arrayRatings = Json.createArrayBuilder();
        JsonObjectBuilder objectRate = Json.createObjectBuilder();
        for (Ratings s : ratings) {
            objectRate.add("Source", s.source);
            objectRate.add("Value", s.value);
            objectRate.add("Votes", s.votes);
            JsonObject jsonRate = objectRate.build();

            arrayRatings.add(jsonRate);
        }

        JsonObject jsonDirector = objectDirector.build();
        JsonArray jsonGenres = arrayGenres.build();
        JsonArray jsonWriters = arrayWriters.build();
        JsonArray jsonActors = arrayActors.build();
        JsonArray jsonLanguages = arrayLanguages.build();
        JsonArray jsonCountries = arrayCountries.build();
        JsonArray jsonRatings = arrayRatings.build();

        JsonObject json = Json.createObjectBuilder()
                .add("Title", title)
                .add("Year", year)
                .add("Released", released)
                .add("Runtime", runtime)
                .add("Genres", jsonGenres)
                .add("Director", jsonDirector)
                .add("Writer", jsonWriters)
                .add("Actors", jsonActors)
                .add("Plot", plot)
                .add("Languages", jsonLanguages)
                .add("Countries", jsonCountries)
                .add("Awards", awards)
                .add("Poster", poster)
                .add("Ratings", jsonRatings)
                .build();

        return json;
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Movies bladeRunner = new Movies();
        try {
            bladeRunner.readFromJsonFile("src/main/java/space/harbour/java/hm4/BladeRunner.json");
            System.out.println(bladeRunner.toString());
            System.out.println(bladeRunner.toJsonString());

            String newFilename = "src/main/java/space/harbour/java/hm4/CopiedFile.json";

            writeObjectToFile(bladeRunner.toJsonObject(), newFilename);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
