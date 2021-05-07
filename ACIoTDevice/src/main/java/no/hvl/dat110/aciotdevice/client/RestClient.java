package no.hvl.dat110.aciotdevice.client;


import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.io.IOException;

public class RestClient {


    private static String logpath = "/accessdevice/log/";
    private static String codepath = "/accessdevice/code";
    private final static OkHttpClient client = new OkHttpClient();
    private final static Gson gson = new Gson();


    public RestClient() {
        // TODO Auto-generated constructor stub
    }

    public void doPostAccessEntry(String message) {


        AccessMessage accessMessage = new AccessMessage(message);

        RequestBody requestBody = RequestBody.create(MediaType
                .parse("application/json; charset=utf-8"), gson.toJson(accessMessage));

        Request request = new Request
                .Builder()
                .url("http://localhost:8080" + logpath)
                .post(requestBody)
                .build();


        System.out.println(request.toString());

        try {
            Response response = client.newCall(request).execute();

            String responseBodyAsString = response.body().string();

            System.out.println(responseBodyAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public AccessCode doGetAccessCode() {

        Request request = new Request.Builder()
                .url("http://localhost:8080" + codepath)
                .get()
                .build();

        System.out.println(request.toString());

        try {
            Response response = client.newCall(request).execute();
            String responseBodyAsString = response.body().string();

            System.out.println(responseBodyAsString);

            return gson.fromJson(responseBodyAsString, AccessCode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
