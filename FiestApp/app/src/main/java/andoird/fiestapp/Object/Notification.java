package andoird.fiestapp.Object;

/**
 * Created by mathieu on 15/12/2017.
 */

public class Notification {
    private String Title;
    private String content;

    public Notification(String title, String content) {

        Title = title;
        this.content = content;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
