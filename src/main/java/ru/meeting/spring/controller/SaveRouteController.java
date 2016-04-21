package main.java.ru.meeting.spring.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.ru.meeting.Coordinates;
import main.java.ru.meeting.Location;
import main.java.ru.meeting.Result;
import main.java.ru.meeting.db.HibernateUtil;
import main.java.ru.meeting.db.entity.Routes;
import main.java.ru.meeting.db.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@RestController
@RequestMapping(value = "/save")
public class SaveRouteController {
    private String elevationGoogle = "https://maps.googleapis.com/maps/api/elevation/json?path=";
    private String key = "AIzaSyBh4pQ1Xt5uLy7nEOC5fJ7NGkKS_vmeTKs";
    Gson gson = new Gson();
    Type listType = new TypeToken<Result>() {}.getType();

    @RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<List<Location>> saveRoute(@RequestBody List<Location> route, HttpSession httpSession) {
        //System.out.println(route);
        //System.out.println(route.get(1));
        User user = (User)httpSession.getAttribute("userForm");
        String locations = new String();
        //System.out.println("user = "+user);
        System.out.println("call heights");
        locations = getCoordinatesOfRoute(route);

        Routes dbRoute = new Routes();
        dbRoute.setRoute(locations);

        System.out.println("Before getSessionFactory");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("Before openSession()");
        Session session = sessionFactory.openSession();
        System.out.println("Before beginTransaction()");
        session.beginTransaction();
        System.out.println("Before save()");
        session.save(dbRoute);
        System.out.println("Before getTransaction()");
        session.getTransaction().commit();
        System.out.println("Before return");

        return new ResponseEntity<List<Location>>(route, HttpStatus.OK);
    }

    private /*List<Coordinates> */String getCoordinatesOfRoute(List<Location> route) {
        try {

            StringBuilder sb = new StringBuilder(elevationGoogle);
            sb.append(getStringRoute(route));
            sb.append("&samples=").append(route.size() * 3).append("&key=").append(key);
            System.out.println("Create http request ");
            //System.out.println(sb.toString());
            URL url = new URL(sb.toString());
            URLConnection urlConnection = url.openConnection();

            Result elevations = gson.fromJson(new InputStreamReader(urlConnection.getInputStream()), listType);
            sb = new StringBuilder();
            for (Coordinates h : elevations.getResults()) {
                sb.append(h.locationToString());
                //System.out.println(h);
                //System.out.println(h.locationToString());
            }
            //System.out.println(elevations.getResults());
            return sb.toString();//elevations.getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private String getStringRoute(List<Location> route) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < route.size() - 1; i++) {
            addPoint(route.get(i), sb);
            sb.append("|");
        }
        addPoint(route.get(route.size() - 1), sb);
        return sb.toString();
    }

    private void addPoint(Location p, StringBuilder sb) {
        sb.append(p.getLat());
        sb.append(",");
        sb.append(p.getLng());
    }
}
