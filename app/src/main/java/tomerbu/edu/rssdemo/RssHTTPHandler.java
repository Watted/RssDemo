package tomerbu.edu.rssdemo;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import java.util.ArrayList;


/**
 * Created by Programmer on 03/12/2017.
 */

public class RssHTTPHandler {

    //Observer
    public interface RssResultListener{
        void onResult(@NonNull ArrayList<Rss> result);
        void onError(@NonNull Exception e);
    }

    public static void read(String address, RssResultListener listener) {
        //get a reference to the Main Thread:
        //the handler helps us get a reference to the current thread.
        //in order to update the ui, let's get a reference of the main thread as well
        final android.os.Handler main = new android.os.Handler();
        new Thread(() -> {
            try {
                URL url = new URL(address);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1");
                InputStream in = con.getInputStream();
                String xml = read(in, "windows-1255");
                Document document = Jsoup.parse(xml);
                Elements itemsArray = document.getElementsByTag("item");
                ArrayList<Rss> rss = new ArrayList<>();
                for (Element e : itemsArray) {
                    //there is only one title element per item.
                    Element title = e.getElementsByTag("title").first();
                    String titleValue = title.text();
                    if (titleValue.startsWith("<![CDATA")){
                        try {
                            titleValue = titleValue.substring(9, titleValue.length() - 3);
                        }catch (Exception e1){e1.printStackTrace();}
                    }
                    Element desc = e.getElementsByTag("description").first();
                    Document descDoc = Jsoup.parse(desc.text());
                    String link = "";
                    String img = "";
                    try {
                        link = descDoc.getElementsByTag("a").first().attr("href");
                        img = descDoc.getElementsByTag("img").first().attr("src");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    String summary = descDoc.text();
                    rss.add(new Rss(titleValue, img, summary, link));
                }
                //if we got so far , report the result to the listener. best to do so on the ui thread.
                main.post(/*code that runs on the main thread*/()->{
                    listener.onResult(rss);
                });
            } catch (Exception e) {//TODO: Handle Errors!
                main.post(/*code that runs on the main thread*/()->{
                    listener.onError(e);
                });
            }
        }).start();
    }

    private static String read(InputStream in, String charset) throws IOException {
        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in, charset));
        String line = null;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }
}
