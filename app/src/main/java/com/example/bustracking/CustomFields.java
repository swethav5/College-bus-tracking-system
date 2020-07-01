package com.example.bustracking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CustomFields {
    String email;
    String pass;
    String user;

    public CustomFields(String email, String pass, String user) {
        this.email = email;
        this.pass = pass;
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}