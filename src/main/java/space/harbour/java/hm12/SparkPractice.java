package space.harbour.java.hm12;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;

import com.google.gson.Gson;
import org.eclipse.jetty.util.StringUtil;


public class SparkPractice {

    public static class Message {
        public String message;

        public Message(String message) {
            this.message = message;
        }
    }

    public static void main(String[] args) {
        //staticFileLocation("/public");

        Gson gson = new Gson();
        get("/sum/:left/:right", (request, response) -> "Sum is: "
                + String.valueOf(Integer.parseInt(request.params(":left"))
                + Integer.parseInt(request.params(":right"))));
        get("/hello/:name", (request, response) -> "Hello " + request.params(":name"));
        get("/message", (request, response) -> new Message("Hello GSON"), gson::toJson);
        get("/protected", (request, response) -> {
            halt(403);
            return null;
        });
        get("/redirected", (request, response) -> {
            response.redirect("sum/1024/2048");
            return null;
        });

        before((request, response) -> {
            String user = request.queryParams("user");
            String password = request.queryParams("pwd");

            final String dbPassword = "th1sIs4Rea11yHardPa$$w0rd";

            if (StringUtil.isEmpty(password) || dbPassword.equals(password)) {
                halt(403, "Get lost");
            }
        });
    }
}
