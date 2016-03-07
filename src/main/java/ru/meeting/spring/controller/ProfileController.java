package main.java.ru.meeting.spring.controller;

import main.java.ru.meeting.db.entity.User;
import main.java.ru.meeting.server.MainServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

/**
 * Created by sergej on 13.06.15.
 */
@Controller

public class ProfileController {
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String chkProfile(@ModelAttribute("userForm") User user, Map<String , Object> model, HttpSession httpSession) {
        Object sessionUser = httpSession.getAttribute("userForm");
        if (sessionUser != null) {
            model.put("userForm", sessionUser);
            return "profile";
        }
        return "index";
    }
    @RequestMapping(value = "/close", method = RequestMethod.GET)
    public String close(@ModelAttribute("userForm") User user, Map<String , Object> model, HttpSession httpSession) {
        httpSession.removeAttribute("userForm");
        return "index";
    }

    @RequestMapping(value = "/profile",method = RequestMethod.POST)
    public String viewProfile(@ModelAttribute("userForm") User user, Map<String , Object> model, HttpSession httpSession) {
        User cur = Checker.checkForLogin(user);
        
        if (cur == null) { // if user gone from index with bad login or user entering /profile
            Object sessionUser = httpSession.getAttribute("userForm");
            if (sessionUser != null) {
                return "profile";
            }
            return "index";

        }

        long sessionId = MainServer.getSessionId(cur.getId());
        System.out.println("sessionId = "+sessionId+"...............");
        cur.setSessionId(sessionId);
        httpSession.setAttribute("userForm", cur); //if user entering ok login
        model.put("userForm", cur);
        return "profile";
    }

}