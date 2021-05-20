package com.helloservlet.HelloServlet.model;

import java.util.Date;

public class User extends Something {

    public String username;
    public String password;
    public String date_created;

    public User(String username, String password, String date_created) {
        this.username = username;
        this.password = password;
        this.date_created = date_created;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDate_created() {
        return date_created;
    }
}
