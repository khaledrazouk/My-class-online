package com.simplon.myclassonline.model;

public class ClassRoom {

    private int classRoomId;
    private String classroomname;
    private String classRoomImage;
    private User user;
    public ClassRoom(int classRoomId, String classroomname, String classRoomImage) {
        this.classRoomId = classRoomId;
        this.classroomname = classroomname;
        this.classRoomImage = classRoomImage;
    }
    public ClassRoom() {
    }
    public ClassRoom(String classroomname, String classRoomImage, User user) {
        this.classroomname = classroomname;
        this.classRoomImage = classRoomImage;
        this.user = user;
    }
    public ClassRoom(int classRoomId, String classroomname, String classRoomImage, User user) {
        this.classRoomId = classRoomId;
        this.classroomname = classroomname;
        this.classRoomImage = classRoomImage;
        this.user = user;
    }
    // private String classRoomImage;
    public int getClassRoomId() {
        return classRoomId;
    }
    public void setClassRoomId(int classRoomId) {
        this.classRoomId = classRoomId;
    }
    public String getClassRoomName() {
        return classroomname;
    }
    public void setClassRoomName(String classroomname) {
        this.classroomname = classroomname;
    }
    public String getClassRoomImage() {
        return classRoomImage;
    }
    public void setClassRoomImage(String classRoomImage) {
        this.classRoomImage = classRoomImage;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    

}
