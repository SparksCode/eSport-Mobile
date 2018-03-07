package tdkdesigns.hundredthieves.Model;

public class Panel {

    private String Name;
    private String Image;
    private String URL;

    public Panel() {
    }

    public Panel(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
