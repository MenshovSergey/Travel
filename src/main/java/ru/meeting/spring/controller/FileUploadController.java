package main.java.ru.meeting.spring.controller;


import java.io.*;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpSession;

@Controller
public class FileUploadController {
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1048576); //1 Mb
        multipartResolver.setMaxInMemorySize(1048576);
        multipartResolver.setMaxUploadSize(1048576);
        return multipartResolver;
    }

    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public String setupUploadFil(){
        System.out.println();
        System.out.println("GEEETTTTT");
        return "upload";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file, HttpSession httpSession){
        System.out.println("POSSSSSSSSS");
        System.out.println(file);

//        model.put("userForm", user);
//        System.out.println(user);
        boolean isSave = saveFile(file);
        if (isSave){
            return "profile";
        }
        return "edit";
        //return "hddddd";
    }
    private boolean saveFile( MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("hy")));
                stream.write(bytes);
                stream.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return true;
        }
    }

}


