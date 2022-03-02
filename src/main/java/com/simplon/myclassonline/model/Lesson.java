package com.simplon.myclassonline.model;

public class Lesson {
    private int lessonId;
    private String lessonName;
    private String content;
 
    private int classroom_id;
    private int user_id;
    public Lesson(String lessonName, String content, int classroom_id, int user_id) {
        this.lessonName = lessonName;
        this.content = content;
        this.classroom_id = classroom_id;
        this.user_id = user_id;
    }
    public Lesson(String lessonName, String content) {
        this.lessonName = lessonName;
        this.content = content;
    }
    public Lesson() {
    }
    public Lesson(int lessonId, String lessonName, String content) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.content = content;
    }
    public int getLessonId() {
        return lessonId;
    }
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }
    public String getLessonName() {
        return lessonName;
    }
    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
   
    public int getClassroom_id() {
        return classroom_id;
    }
    public void setClassroom_id(int classroom_id) {
        this.classroom_id = classroom_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
   
}
