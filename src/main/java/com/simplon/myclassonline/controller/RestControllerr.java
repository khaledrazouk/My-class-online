package com.simplon.myclassonline.controller;

import java.util.List;

import com.simplon.myclassonline.dao.UserRepository;

import com.simplon.myclassonline.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerr {
    @Autowired
   public UserRepository repo;
    
   @GetMapping("/api/displayAllTeachers")
    public List<User> showAllTeachers(){
        List<User> teachers = repo.findAll();
        return teachers;
    }
}
