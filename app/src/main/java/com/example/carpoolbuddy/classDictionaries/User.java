package com.example.carpoolbuddy.classDictionaries;

public class User {
    public String displayName;
    public String displayEmail;


    public User(String name, String email){
        this.displayName = name;
        this.displayEmail = email;

    }

    public String getDisplayName(){
        return displayName;
    }


}
