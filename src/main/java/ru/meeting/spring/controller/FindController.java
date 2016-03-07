package main.java.ru.meeting.spring.controller;

import main.java.ru.meeting.db.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by sergej on 22.06.15.
 */
@Controller
@RequestMapping(value = "/find")
public class FindController {
    @RequestMapping(method = RequestMethod.GET)
    public String getFind(@ModelAttribute("userForm") User user, HttpSession httpSession) {
        System.out.println("GET Find" + httpSession.getAttribute("userForm"));
        System.out.println("user"+user);
        return "find";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(@ModelAttribute("userForm") User user) {
        return "find";
    }
}
