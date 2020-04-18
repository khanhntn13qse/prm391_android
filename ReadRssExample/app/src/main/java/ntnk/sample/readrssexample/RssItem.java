package ntnk.sample.readrssexample;

public class RssItem {
    private String title;
    private String desciption;
    private String publishDate;

    public RssItem() {
    }

    public RssItem(String title, String desciption, String publishDate) {
        this.title = title;
        this.desciption = desciption;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
