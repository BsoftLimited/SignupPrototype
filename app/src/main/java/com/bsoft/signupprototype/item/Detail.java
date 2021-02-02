package com.bsoft.signupprototype.item;

import java.io.Serializable;

public class Detail extends Credential{
    String name, surname, email, state, lga, number;
    public Detail(String uid, String username, String password){
        super(uid, username, password);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getState() {
        return state;
    }

    public String getLga() {
        return lga;
    }

    public String getNumber() {
        return number;
    }
}
