package tdkdesigns.hundredthieves.Model;

import tdkdesigns.hundredthieves.Schedule;

public class SchedulePanel {
    private String Name;
    private String Image;

    public SchedulePanel() {
    }

    public SchedulePanel(String name, String image) {
        Name = name;
        Image = image;
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
