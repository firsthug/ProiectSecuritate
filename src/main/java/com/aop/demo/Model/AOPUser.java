package com.aop.demo.Model;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="type",discriminatorType = DiscriminatorType.STRING)
@Table(schema = "ADMIN1")
public class AOPUser {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQUENCE1")
    @SequenceGenerator(name="SEQUENCE1", sequenceName="ADMIN1.SEQUENCE1", allocationSize=1)
    private Integer userID;

    private String fullname;

    @Column(name="USERNAME")
    private String username;

    private String password;

    private String email;

    private String phone;

    @Column(insertable = false, updatable = false)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public AOPUser(Integer userID, String fullname, String username, String password, String email, String phone, String type) {
        this.userID = userID;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.type = type;
    }

    public AOPUser(Integer userID) {
        this.userID = userID;
    }

    public AOPUser(){
    }

}
