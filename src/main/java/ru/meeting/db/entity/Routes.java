package main.java.ru.meeting.db.entity;

import javax.persistence.*;

@Entity
@Table (name = "Routes")
public class Routes {
    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "route")
    private String route;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(route);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public long getId() {
        return id;
    }

    public String getRoute() {
        return route;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
