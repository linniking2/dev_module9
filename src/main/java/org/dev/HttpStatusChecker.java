package org.dev;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpStatusChecker {
    public String getStatusImage(int code) throws Exception {
        String url = "https://http.cat/" + code + ".jpg";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode == 404) {
            throw new Exception("No image found for HTTP status code " + code);
        }

        return url;
    }

    public static void main(String[] args) {
        HttpStatusChecker checker = new HttpStatusChecker();
        try {
            System.out.println(checker.getStatusImage(200)); // Should print the URL
            System.out.println(checker.getStatusImage(10000)); // Should throw an exception
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
