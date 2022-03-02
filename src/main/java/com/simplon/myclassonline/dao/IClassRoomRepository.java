package com.simplon.myclassonline.dao;

import java.util.List;

import com.simplon.myclassonline.model.ClassRoom;

public interface IClassRoomRepository {
    List<ClassRoom>findClassesRoomByTeacher(int teacherId);
    boolean add(ClassRoom classRoom);
    void updateById(ClassRoom classRoom);
    void deleteById(int id);
    ClassRoom findById(int id);
    List<ClassRoom> findAll();
}
