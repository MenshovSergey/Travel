package main.java.ru.meeting;

/**
 * Created by sergej on 12.11.15.
 */
public class Location {
    private double lat;
    private double lng;

    public String toString() {
        return "latitude = " + lat +", longitude = " +lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
