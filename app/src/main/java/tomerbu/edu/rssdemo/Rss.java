package tomerbu.edu.rssdemo;

/**
 * Created by Programmer on 03/12/2017.
 */

public class Rss {
    String title;
    String imageUrl;
    String summary;
    String link;

    //also default ctor may be useful:
    public Rss(String title, String imageUrl, String summary, String link) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.summary = summary;
        this.link = link;
    }

    public Rss() {}

    //toString()...
    @Override
    public String toString() {
        return "Rss{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", summary='" + summary + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
