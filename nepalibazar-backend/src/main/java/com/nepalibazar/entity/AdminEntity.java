package com.nepalibazar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "admin_user")
    private String userName;

    @Column(name = "admin_passsword")
    private String password;

    @Column(name = "is_logged-in")
    private Boolean isLoggedIn;

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
