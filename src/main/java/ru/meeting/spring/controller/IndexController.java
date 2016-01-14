package main.java.ru.meeting.spring.controller;

import main.java.ru.meeting.db.User;
import main.java.ru.meeting.server.MainServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

/**
 * Created by tseyler on 07.06.15.
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(Map<String , Object> model, HttpSession httpSession) {
        System.out.println(model);
        Object user = httpSession.getAttribute("userForm");
        if (user != null) {
            System.out.println((User)user);
            return "profile";
        }
        User userForm = new User();
        model.put("userForm", userForm);
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("userForm") User user, Map<String, Object> model, HttpSession httpSession) {
        User cur = Checker.checkForLogin(user);

        if (cur == null) {
            return "index";
        }
        httpSession.setAttribute("userForm", cur);


        System.out.println("before profile!!!!");
        model.put("userForm", cur);
        return "profile";
    }

}
