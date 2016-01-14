package main.java.ru.meeting.db;

import javax.persistence.*;

@Entity
@Table (name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "yearsOld")
    private Integer yearsOld;

    @Column(name = "login")
    private String login;

    @Column(name = "photo", length = 250)
    private String photo;

    @Column(name = "about", length = 1000)
    private String about;

    private long sessionId;

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(username);
        stringBuilder.append("\n");
        stringBuilder.append(password);
        stringBuilder.append("\n");
        stringBuilder.append(yearsOld);
        stringBuilder.append("\n");
        stringBuilder.append(login);
        stringBuilder.append("\n");
        stringBuilder.append(about);
        stringBuilder.append("\n");
        stringBuilder.append(photo);
        stringBuilder.append("\n");
        stringBuilder.append("sessionID="+sessionId);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
    public String getLogin() {
        return login;
    }

    public Integer getYearsOld() {
        return yearsOld;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setYearsOld(Integer yearsOld) {
        this.yearsOld = yearsOld;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }
}
