package com.simplon.myclassonline.dao;

import java.util.List;

import com.simplon.myclassonline.model.Lesson;


public interface ILessonRepository {
    boolean add(Lesson lesson);
    void updateById(Lesson lesson);
    void deleteById(int id);
    Lesson findById(int id);
    List<Lesson>FindLessonByClass(int idClass);
    List<Lesson> findAll();
}
