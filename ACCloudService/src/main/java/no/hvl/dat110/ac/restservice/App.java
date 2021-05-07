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

        get("/accessdevice/log/", (req, res) -> accesslog.toJson());

        get("/accessdevice/log/:id", (req, res) -> {


            int id = Integer.parseInt(req.params("id"));

            return gson.toJson(accesslog.get(id));

        });


        post("/accessdevice/log/", ((request, response) -> {
            String message = gson.fromJson(request.body(), AccessMessage.class).getMessage();
            int id = accesslog.add(message);

            AccessEntry entry = accesslog.get(id);

            return gson.toJson(entry);
        }));
    }

}
