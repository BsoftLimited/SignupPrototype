package com.bsoft.signupprototype.item;

import java.io.Serializable;

public class Detail implements Serializable {
    String uid, name, surname, username, password, email;

    public Detail(String uid, String name, String surname){
        this.name = name;
        this.surname = surname;
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
