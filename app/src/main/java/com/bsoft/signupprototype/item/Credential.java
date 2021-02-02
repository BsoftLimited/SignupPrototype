package com.bsoft.signupprototype.item;

import java.io.Serializable;

public class Credential implements Serializable {
    String username, password, uid;

    public Credential(String uid, String username, String password){
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public Credential getCredential(){
        return new Credential(uid, username, password);
    }
}
