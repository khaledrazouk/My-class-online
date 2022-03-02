package com.simplon.myclassonline.controller;




import com.simplon.myclassonline.dao.ClassRoomRepository;
import com.simplon.myclassonline.dao.LessonRepository;
import com.simplon.myclassonline.model.ClassRoom;
import com.simplon.myclassonline.model.Lesson;
import com.simplon.myclassonline.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;


@Controller

public class LessonController {
    
    @Autowired
    public LessonRepository repo;
    @Autowired
    public ClassRoomRepository RepoClass;

    //showing the addLesson page
    @GetMapping ("/addLesson")
    public String addLesson(Model model){
        Lesson lesson = new Lesson();
        model.addAttribute("lesson", lesson);
        return "addLesson";
    }
    //to add a new lesson in the addLessonPage
    @PostMapping ("/addLesson")
    public String addLesson(@ModelAttribute("addLesson")Lesson lesson,Model model, @AuthenticationPrincipal User user){
        lesson.setUser_id(user.getUserId());
        repo.add(lesson);
        return"redirect:/addLesson";
    }
   

    //modify showLessonList and make a parametrized route with the classroom Id (/classRoom/id)
    // to check
    @GetMapping("/classRoom/{id}")
    public String showLessonList(Model model,@PathVariable Integer id){
        model.addAttribute("lesson", repo.FindLessonByClass(id));
        return "showLessonList";
    }
    
// this method is for showing the lesson content
    @GetMapping("/lesson/{id}")
    public String findById(@PathVariable int id , Model model) {
        Lesson lesson = repo.findById(id);
    model.addAttribute("lesson", lesson);
        return "lesson.html";
    }
    
    //i have to put something for asking the password when someone want to delete a lesson
    @GetMapping("/deleteLesson/{id}")
public String deleteEmployee(@PathVariable(value = "id") Integer id,@AuthenticationPrincipal User user)
    {
    Lesson lesson = repo.findById(id);
    if(lesson== null){
       throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    repo.deleteById(id);

    
    return "redirect:/";
}
//this method will save the class room by the select action btn
@PostMapping("/toto")
public String saveClassById(@ModelAttribute("sortClassRoom") ClassRoom classRoom) {
    
   RepoClass.add(classRoom);
    return "addLesson";
} 
}
