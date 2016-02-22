package main.java.ru.meeting.spring.controller;

import main.java.ru.meeting.db.HibernateUtil;
import main.java.ru.meeting.db.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by sergej on 13.06.15.
 */
@Controller
@RequestMapping(value = "/edit")
public class EditorController {
    public static final int MAX_COUNT = 1000;
    public static final String startPath = "/files";
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1048576 * 10); //4 Mb
        multipartResolver.setMaxInMemorySize(1048576 * 10);
        multipartResolver.setMaxUploadSize(1048576 * 10);
        return multipartResolver;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewProfile(Map<String , Object> model, HttpSession httpSession) {
        User sessionUser = (User)httpSession.getAttribute("userForm");
        model.put("userForm", sessionUser);
        System.out.println("sessionUser = " + sessionUser);

        return "edit";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String editProfile( @ModelAttribute("userForm") User user, Map<String , Object> model, HttpSession httpSession,
                               @RequestParam("file") MultipartFile file) {
       User oldUser =  (User)httpSession.getAttribute("userForm");
       File saveDirectory = new File( "src/main/webapp/files/0/");
       System.out.println("oldUser = " + oldUser);
       System.out.println("user=" + user);
       System.out.println("model="+model);
       if (file != null) {
           String newName = saveFile(file, saveDirectory, oldUser.getLogin() + ".jpg", oldUser.getPhoto());
           String needName = newName.substring(newName.indexOf(startPath));
           oldUser.setPhoto(needName);
           System.out.println("newName = "+newName);
           System.out.println("needName= "+needName);
       }
       oldUser.setUsername(user.getUsername());
       oldUser.setYearsOld(user.getYearsOld());
       saveUser(oldUser);
       model.put("userForm", oldUser);

       return "profile";

    }

    private void saveUser(User oldUser) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.update(oldUser);
        session.getTransaction().commit();
    }

    private String saveFile( MultipartFile file, File directory, String name, String oldName) {
        if (!file.isEmpty()) {
            File[] count = directory.listFiles();
            if (count.length < MAX_COUNT) {

                writeFile(file, directory.getPath()+ "/"+ name);
                return directory.getPath() +"/"+ name;
            } else {
                File parent = directory.getParentFile();
                File[] child = parent.listFiles();
                File last = child[child.length - 1];
                File[] lastCount = directory.listFiles();
                if (lastCount.length < MAX_COUNT) {
                    writeFile(file, last.getPath()+ "/" + name);
                    return last.getPath() +"/"+ name;
                } else {
                    File newFile = new File ("files/"+child.length);
                    writeFile(file, newFile.getPath()+ "/" + name);
                    return newFile.getPath() + name;
                }
            }
        }
        return oldName;
    }
    private void writeFile(MultipartFile file, String name) {
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File( name)));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
