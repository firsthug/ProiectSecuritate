package com.aop.demo.Model;


import javax.persistence.*;

@Entity
public class AOPUser {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQUENCE1")
    @SequenceGenerator(name="SEQUENCE1", sequenceName="SEQUENCE1", allocationSize=1)
    private Integer userID;

    private String fullname;

    private String username;

    private String password;

    private String email;

    private String phone;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AOPUser(Integer userID, String fullname, String password, String email, String phone) {
        this.userID = userID;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public AOPUser(Integer userID) {
        this.userID = userID;
    }

    public AOPUser(){
    }

}
