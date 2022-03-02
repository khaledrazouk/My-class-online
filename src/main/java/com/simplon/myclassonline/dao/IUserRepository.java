package com.simplon.myclassonline.dao;

import java.util.List;

import com.simplon.myclassonline.model.User;

public interface IUserRepository {
    boolean add(User user);
 
    void updateById(User user);
  
  boolean deleteById(int id);
  
  List<User> findAll();
  
  User findById(int id);
  
  User findByEmail(String email);
}
