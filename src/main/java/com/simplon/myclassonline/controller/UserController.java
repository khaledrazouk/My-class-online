package com.simplon.myclassonline.controller;

import javax.validation.Valid;

import com.simplon.myclassonline.dao.UserRepository;
import com.simplon.myclassonline.model.ClassRoom;
import com.simplon.myclassonline.model.User;
import com.simplon.myclassonline.servise.Uploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {
    @Autowired
    private Uploader uploader;
    @Autowired
    public UserRepository repo;
    @Autowired
    private PasswordEncoder encoder;

    //showing the registration page
    @GetMapping("/Registration")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "Registration";
    }

    //adding a user in the registration form and it will be like adding a teacher
    @PostMapping("/Registration")
    public String addUser(@Valid User user, BindingResult result, Model model , @RequestParam("file") MultipartFile file) 
    {
        if (result.hasErrors()) {
            return "Registration";
        }

        if (repo.findByEmail(user.getEmail()) != null) {
            model.addAttribute("feedback", "User already exists");
            return "Registration";
        }
        try {
            String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setRole("ROLE_ADMIN");
        user.setImage(uploader.upload(file));
        repo.add(user);
    
        } catch (Exception e) {
            e.printStackTrace();

        }
        
        return "redirect:/";
    }



// to check it if i need it or not because the user in the regestration page it will be a teacher in the same time
    @GetMapping("/showAllTeachers")
    public String showAllTeachers(Model model){
        model.addAttribute("teachers",repo.findAll());
        return "showAllTeachers";
    }

// just to show the page with javascript
    // @GetMapping("/showAllTeachers")
    // public String showAllTeachers(){
    //     return "showAllTeachers";
    // }

    

    // this method is for showing the profil
    @GetMapping("/profile/{id}")
    public String findById(@PathVariable int id , Model model) {
        User user = repo.findById(id);
    model.addAttribute("teacher", user);
        return "profile";
    }
    // @GetMapping("/teachers/{id}")
    // public String showTeachersList(Model model,@PathVariable Integer id){
    //     model.addAttribute("teacher", repo.findAll(id));
    //     return "showTeacherList";
    // }
    
 
}
