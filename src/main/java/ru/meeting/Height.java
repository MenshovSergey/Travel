package main.java.ru.meeting;

/**
 * Created by sergej on 19.11.15.
 */
public class Height {
    private double elevation;
    private Location location;

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("elevation = ");
        sb.append(elevation);
        sb.append("\n");
        sb.append(location);
        return sb.toString();
    }
}
