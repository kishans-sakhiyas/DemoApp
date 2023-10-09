package com.example.demoapp.model;

public class UserModel {
    String name;
    String number;
    String email;
    String password;
    String dt;

    public UserModel(String name, String number, String email, String password, String dt) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.password = password;
        this.dt = dt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}
