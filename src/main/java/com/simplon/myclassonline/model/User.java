package com.simplon.myclassonline.model;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {

     private int userId;
    @NotBlank(message = "You must fill in this field")
    
     private String firstName;
     @NotBlank(message = "You must fill in this field")
     private String lastName;
     @Email(message = "You must fill in this field")
     @NotBlank
     @Size(min = 4, max = 45)

     private String email;
     @NotBlank(message = "You must fill in this field")
    @Size(min = 4, max = 45)
     private String password;
     private String image;
     private String specialization;
     private String description;
     private String role;
     
   
    public User(@NotBlank(message = "You must fill in this field") String firstName,
            @NotBlank(message = "You must fill in this field") String lastName,
            @Email(message = "You must fill in this field") @NotBlank @Size(min = 4, max = 45) String email,
            @NotBlank(message = "You must fill in this field") @Size(min = 4, max = 45) String password, String image,
            String specialization, String description, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.image = image;
        this.specialization = specialization;
        this.description = description;
        this.role = role;
    }
    public User(int userId, @NotBlank(message = "You must fill in this field") String firstName,
            @NotBlank(message = "You must fill in this field") String lastName,
            @Email(message = "You must fill in this field") @NotBlank @Size(min = 4, max = 45) String email,
            @NotBlank(message = "You must fill in this field") @Size(min = 4, max = 45) String password, String image,
            String specialization, String description, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.image = image;
        this.specialization = specialization;
        this.description = description;
        this.role = role;
    }
    public User() {
    }


    
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


    //copy past the same things whiche we did today auth
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }
    @Override
    public boolean isAccountNonLocked() {

        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }
    @Override
    public boolean isEnabled() {

        return true;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
