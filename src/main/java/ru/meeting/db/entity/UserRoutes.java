package main.java.ru.meeting.db.entity;
import javax.persistence.*;

@Entity
@Table (name = "UserRoutes")
public class UserRoutes {
    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "idUser")
    private long idUser;

    @Column(name = "idRoute")
    private long idRoute;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(idUser);
        stringBuilder.append("\n");
        stringBuilder.append(idRoute);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public long getId() {
        return id;
    }

    public long getIdUser() {
        return idUser;
    }

    public long getIdRoute() {
        return idRoute;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public void setIdRoute(long idRoute) {
        this.idRoute = idRoute;
    }
}
