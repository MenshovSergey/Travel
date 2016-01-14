package main.java.ru.meeting.spring.controller;


import main.java.ru.meeting.db.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import main.java.ru.meeting.db.HibernateUtil;

/**
 * Created by tseyler on 07.06.15.
 */
public class Checker {
    private static boolean checkNotEmpty(User user){
        if (user.getPassword().equals("") || user.getLogin().equals("")){
            return false;
        }
        return true;
    }
    private static User checkNotNull(User user){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        User searched = (User) criteria.add(Restrictions.eq("login", user.getLogin())).uniqueResult();
        if (searched == null){
            return null;
        }else {
            if (searched.getPassword().equals(user.getPassword())){
                return searched;
            }
        }
        return null;
    }
    public static User checkForLogin(User user){
        if (user == null){
            return user;
        }
        User ans = checkNotNull(user);
        boolean notEmpty = checkNotEmpty(user);
        if (!notEmpty) {
            return null;
        }
        return ans;
    }
    public static boolean checkForRegister(User user){
        User ans = checkNotNull(user);
        return checkNotEmpty(user) && (ans==null);
    }


}
