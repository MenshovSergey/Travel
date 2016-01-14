package main.java.ru.meeting.spring.controller;

import main.java.ru.meeting.db.HibernateUtil;
import main.java.ru.meeting.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.Map;

@Controller
@RequestMapping(value = "/registration")
public class RegisterController {

	@RequestMapping(method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model) {
		User userForm = new User();
		model.put("userForm", userForm);

		return "Registration";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userForm") User user, Map<String, Object> model) {
		if (Checker.checkForRegister(user)) {
			user.setPhoto("files/0/start.jpg");
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(user);

			session.getTransaction().commit();
			return "index";
		} else {
			return "Registration";
		}
	}
}
