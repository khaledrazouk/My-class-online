package com.simplon.myclassonline.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ShowPagesHtml {


    @GetMapping ("/user")
    public String admin(){
        return "user";
}
    @GetMapping ("/about")
    public String about(){
        return "about";
}

@GetMapping ("/contact")
public String contact(){
    return "contact.html";
}
@GetMapping ("/teacher")
public String teacher(){
    return "teacher.html";
}
@GetMapping ("/myClasses")
public String lesson(){
    return "myClasses.html";
}
@GetMapping ("/test")
public String test(){
    return "test.html";
}



}
