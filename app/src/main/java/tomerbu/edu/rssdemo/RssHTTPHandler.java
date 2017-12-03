package tomerbu.edu.rssdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Programmer on 03/12/2017.
 */

public class RssHTTPHandler {

    public static void read(String address) throws IOException {
        URL  url = new URL(address);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        InputStream in = con.getInputStream();
    }
    private static String read(InputStream in, String charset) throws IOException {
        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in, charset));
        String line = null;

        while ((line = reader.readLine())!=null){
            builder.append(line);
        }
        return builder.toString();
    }
}
