package com.simplon.myclassonline.controller;

import javax.validation.Valid;

import com.simplon.myclassonline.dao.ClassRoomRepository;
import com.simplon.myclassonline.model.ClassRoom;
import com.simplon.myclassonline.model.User;
import com.simplon.myclassonline.servise.Uploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ClassRoomController {
    @Autowired
    public ClassRoomRepository repo;
    @Autowired
    private Uploader uploader;

// this will show the index page
@GetMapping ("/")
public String index(Model model){
    model.addAttribute("classRooms",repo.findAll());

    return  "index";
}

    // this will show my classRooms pages in the inex page AddClassRoomForm

    @GetMapping("/AddClassRoomForm")
    public String newClassRoom(ClassRoom classRoom, Model model) {
        model.addAttribute("classRoom", classRoom);
        return "AddClassRoomForm";
    }

    // this is for adding a new classroom in a form 
    @PostMapping("/AddClassRoomForm")
    public String addANewClassRoom( ClassRoom classRoom,Model model, @RequestParam("file") MultipartFile file,@AuthenticationPrincipal User user) {
        
        try {
            classRoom.setClassRoomImage(uploader.upload(file));
            classRoom.setUser(user);
            repo.add(classRoom);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

     //this will show a page called course with all of the class rooms with photos 
     @GetMapping ("/course")
     public String course (Model model){
         model.addAttribute("courses",repo.findAll());
         return "course";
     }

//      @GetMapping ("/")
// public String index(Model model){
//     model.addAttribute("classRooms",repo.findAll());

//     return  "index";
// }

}
