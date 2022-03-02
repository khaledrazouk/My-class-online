package com.simplon.myclassonline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.simplon.myclassonline.model.Lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Controller;


@Controller
public class LessonRepository implements ILessonRepository {
    @Autowired
    private DataSource dataSource;
    private Connection connection;


    @Override
    public boolean add(Lesson lesson) {
      try {
            connection = dataSource.getConnection();
            // important i don't have to put the foreign key insid the SQL order
            PreparedStatement ps = connection.prepareStatement("INSERT INTO lesson (lessonname,content,classroom_id,user_id) VALUES (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
           
            ps.setString(1, lesson.getLessonName());
            ps.setString(2, lesson.getContent());
            ps.setInt(3, lesson.getClassroom_id());
            ps.setInt(4, lesson.getUser_id());
            if (ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                lesson.setLessonId(rs.getInt(1));
                return true;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return false;
    }

    // @Override
    // public void updateById(Lesson lesson) {
    //     try {
    //         connection = dataSource.getConnection();
    //         PreparedStatement PS = connection.prepareStatement(
    //                 "UPDATE lesson SET lessone=? , content=? , user_id= ? ,classroom_id WHERE document_id=?");
    //         PS.setString(1, lesson.getLessonName());
    //         PS.setString(2, lesson.getContent());
    //         PS.setInt(3, lesson.getUser().getUserId());
    //         PS.setInt(4, lesson.getClassRoom().getClassRoomId());

    //         PS.executeUpdate();

    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }finally {
    //         DataSourceUtils.releaseConnection(connection, dataSource);
    //     }        
    // }

    @Override
    public void deleteById(int id) {
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM lesson WHERE lesson_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }        
    }

    @Override
    public Lesson findById(int id) {
        Lesson lesson = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement PS = connection.prepareStatement("SELECT * FROM lesson WHERE lesson_id=?");

            PS.setInt(1, id);
            ResultSet rs = PS.executeQuery();
            if (rs.next()) {
                return new Lesson(
                        rs.getInt("lesson_id"),
                        rs.getString("lessonname"),
                        rs.getString("content"));
                      
            }
        } catch (Exception e) {
            e.getMessage();
        }finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return lesson;
    }

    // maybe i should use this method to show the lessons list
    @Override
    public List<Lesson> FindLessonByClass(int idClassroom) {
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM lesson WHERE classroom_id=?");
            ps.setInt(1, idClassroom);
            ResultSet rs = ps.executeQuery();
            List<Lesson> lessonList = new ArrayList<>();
            while(rs.next()){
                Lesson lesson = new Lesson(
                rs.getInt("lesson_id"),
                rs.getString("lessonname"),
                 rs.getString("content")
                 );
                lessonList.add(lesson);
            }
            return lessonList;

        }  catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return null;
    }


    /**
     *     @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                       ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return list;
    }
     */
    @Override
    public List<Lesson> findAll() {
        List<Lesson> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM lesson");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Lesson(
                        rs.getInt("lesson_id"),
                        rs.getString("lessonname"),
                        rs.getString("content")
                       ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return list;
    }

    @Override
    public void updateById(Lesson lesson) {

        
    }
    
}
