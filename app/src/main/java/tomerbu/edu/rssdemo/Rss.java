package tomerbu.edu.rssdemo;

/**
 * Created by Programmer on 03/12/2017.
 */

public class Rss {
    String title;
    String imageUrl;
    String summary;

    //ctor:
    public Rss(String title, String imageUrl, String summary) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.summary = summary;
    }
    //also default ctor may be useful:
    public Rss() {
    }

    //toString()...
    @Override
    public String toString() {
        return "Rss{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
