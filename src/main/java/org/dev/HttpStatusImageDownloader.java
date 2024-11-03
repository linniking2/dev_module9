package org.dev;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HttpStatusImageDownloader {
    private HttpStatusChecker checker;

    public HttpStatusImageDownloader() {
        this.checker = new HttpStatusChecker();
    }

    public void downloadStatusImage(int code) throws Exception {
        String imageUrl = checker.getStatusImage(code);

        try (InputStream in = new URL(imageUrl).openStream();
             FileOutputStream out = new FileOutputStream(code + ".jpg")) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            System.out.println("Image downloaded successfully for HTTP status " + code);
        } catch (IOException e) {
            throw new Exception("Error downloading image for HTTP status " + code);
        }
    }

    public static void main(String[] args) {
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
        try {
            downloader.downloadStatusImage(200); // Should download the image
            downloader.downloadStatusImage(10000); // Should throw an exception
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
