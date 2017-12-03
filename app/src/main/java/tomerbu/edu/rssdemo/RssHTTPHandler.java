package tomerbu.edu.rssdemo;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        String xml = read(in, "utf8");
        Document document = Jsoup.parse(xml);

        Elements itemsArray = document.getElementsByTag("item");

        for (Element e : itemsArray) {
            //there is only one title element per item.
            Element  title = e.getElementsByTag("title").first();
            String titleValue = title.val();
            System.out.println(titleValue);
            Log.d("Rss", titleValue);
        }
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
