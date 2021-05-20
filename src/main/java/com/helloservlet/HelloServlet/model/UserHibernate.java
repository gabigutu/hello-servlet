package com.helloservlet.HelloServlet.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserHibernate {

    @Id
    private int id;

    private String username;
    private String password;
    private String date_created;

    public UserHibernate() {
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

    @Override
    public String toString() {
        return "UserHibernate{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", date_created='" + date_created + '\'' +
                '}';
    }
}
