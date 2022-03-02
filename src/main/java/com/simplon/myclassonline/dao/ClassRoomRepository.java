package com.simplon.myclassonline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.simplon.myclassonline.model.ClassRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;


@Repository
public class ClassRoomRepository implements IClassRoomRepository {
    // just for remembering that i have added an classRoomImage to my ClassRoom  

    @Autowired
    private DataSource dataSource;
    private Connection connection;
    @Override
    public List<ClassRoom> findClassesRoomByTeacher(int teacherId) {
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM classroom WHERE user_id=?");
            ps.setInt(1, teacherId);
         
              ResultSet rs = ps.executeQuery();
              List<ClassRoom> classList = new ArrayList<>();
                while(rs.next()){
                    ClassRoom classRoom = new ClassRoom(
                    rs.getInt("classroom_id"),
                    rs.getString("classroomname"),
                    rs.getString("classRoomImage")
                    );
                    classList.add(classRoom);
                }
                return classList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return null;        
    }

    @Override
    public boolean add(ClassRoom classRoom) {
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement( "INSERT INTO classroom (classroomname , classRoomImage, user_id) VALUES (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, classRoom.getClassRoomName());
            ps.setString(2, classRoom.getClassRoomImage());
            ps.setInt(3, classRoom.getUser().getUserId());
            if (ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                classRoom.setClassRoomId(rs.getInt(1));
                return true;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return false;       
    }

    @Override
    public void updateById(ClassRoom classRoom) {
        try {
            connection = dataSource.getConnection();
            PreparedStatement PS = connection.prepareStatement("UPDATE classroom SET classroomname=? , classRoomImage= ? WHERE classroom_id=?");

            PS.setString(1,classRoom.getClassRoomName());
            PS.setString(2, classRoom.getClassRoomImage());
            PS.setInt(3, classRoom.getClassRoomId());
           PS.executeUpdate();
           
        } catch (Exception e) {
            e.getStackTrace();
        }finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }        
    

    @Override
    public void deleteById(int id) {
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM classroom WHERE class_id=?");
            ps.setInt(1, id);
          ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }            
    }

    @Override
    public ClassRoom findById(int id) {
        ClassRoom classRoom = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement PS = connection.prepareStatement("SELECT * classroom WHERE classroom_id = ?");

            PS.setInt(1, id);
            ResultSet rs = PS.executeQuery();
            if (rs.next()) {
                classRoom = new ClassRoom(rs.getInt("classroom_id"), 
                rs.getString("classroomname"),rs.getString("classRoomImage"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return classRoom;
    }

    @Override
    public List<ClassRoom> findAll() {
        List<ClassRoom> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM classroom");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ClassRoom(
                        rs.getInt("classroom_id"),
                        rs.getString("classroomname"),
                        rs.getString("classRoomImage") ));
                     }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return list;
    
}
}