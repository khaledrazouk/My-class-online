package com.simplon.myclassonline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.simplon.myclassonline.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
@Repository
public class UserRepository implements IUserRepository , UserDetailsService {

    @Autowired
    private DataSource dataSource;
    private Connection connection;

    // done

    //i have to change some methods to boolean to make some returns
    //very important also to check where i'm closing my connection and where i don't close it 
    @Override
    public boolean add(User user) {
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO user (fname,lname,email,password,image,specialization,description,role) VALUES (? ,? ,? ,? ,? ,? ,? ,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getImage());
            ps.setString(6, user.getSpecialization());
            ps.setString(7, user.getDescription());
            ps.setString(8, user.getRole());
            if (ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                user.setUserId(rs.getInt(1));
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
    public boolean deleteById(int id) {

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE user_id=?");
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return false;
    }

    // i'm not sure about it
    @Override
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
                        rs.getString("image"),
                        rs.getString("specialization"),
                        rs.getString("description"),
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

  
   
    @Override
    public User findById(int id) {
        User user = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement PS = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            PS.setInt(1, id);
            ResultSet rs = PS.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getInt("user_id"),
                 rs.getString("fname"),
                  rs.getString("lname"),
                   rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("image"),
                    rs.getString("specialization"),
                    rs.getString("description"),
                     rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return user;
    }

    @Override
    public void updateById(User user) {
        try {
            connection = dataSource.getConnection();
            PreparedStatement PS = connection.prepareStatement(
                    "UPDATE user SET fname=?, lname=?, email=?, password=?, image = ?,specialization = ?,description =?, role=? WHERE user_id=?");

            PS.setString(1, user.getFirstName());
            PS.setString(2, user.getLastName());
            PS.setString(3, user.getEmail());
            PS.setString(4, user.getPassword());
            PS.setString(5, user.getImage());
            PS.setString(6, user.getSpecialization());
            PS.setString(7, user.getDescription());
            PS.setString(8, user.getRole());
            PS.setInt(9, user.getUserId());
            PS.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    
    
      
    

    @Override
    public User findByEmail(String email) {
        try {
            connection = dataSource.getConnection();
            PreparedStatement stmt = connection
                    .prepareStatement("SELECT * FROM user WHERE email=?");
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            if (result.next()) { 
                return new User(
                        result.getInt("user_id"),
                        result.getString("fname"),
                        result.getString("lname"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("image"),
                        result.getString("specialization"),
                        result.getString("description"),
                        result.getString("role")); }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
              return null;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;   
    }
    
}
