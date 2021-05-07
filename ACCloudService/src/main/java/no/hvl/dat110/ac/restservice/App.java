package no.hvl.dat110.ac.restservice;

import com.google.gson.Gson;

import static spark.Spark.*;

/**
 * Hello world!
 */
public class App {

    static AccessLog accesslog = null;
    static AccessCode accesscode = null;
    private static final Gson gson = new Gson();

    public static void main(String[] args) {

        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }

        // objects for data stored in the service

        accesslog = new AccessLog();
        accesscode = new AccessCode();

        after((req, res) -> {
            res.type("application/json");
        });

        // for basic testing purposes
        get("/accessdevice/hello", (req, res) -> {

            return gson.toJson("IoT Access Control Device");
        });

        // TODO: implement the routes required for the access control service
        // as per the HTTP/REST operations describined in the project description

        post("/accessdevice/log/", ((request, response) -> {
            String message = gson.fromJson(request.body(), AccessMessage.class).getMessage();
            int id = accesslog.add(message);

            AccessEntry entry = accesslog.get(id);

            return gson.toJson(entry);
        }));
    }

}
