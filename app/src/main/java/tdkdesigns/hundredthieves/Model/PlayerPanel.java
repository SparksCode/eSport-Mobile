package tdkdesigns.hundredthieves.Model;

public class PlayerPanel {
    private String Name, Image, Bio, Role, TeamID;

    public PlayerPanel() {
    }

    public PlayerPanel(String name, String image, String bio, String role, String teamID) {
        Name = name;
        Image = image;
        Bio = bio;
        Role = role;
        TeamID = teamID;
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

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getTeamID() {
        return TeamID;
    }

    public void setTeamID(String teamID) {
        TeamID = teamID;
    }
}
