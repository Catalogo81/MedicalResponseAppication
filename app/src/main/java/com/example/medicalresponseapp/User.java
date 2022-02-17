package com.example.medicalresponseapp;

public class User {

    public String name, surname, ID, password,passwordretype,address, gender, email;

    public User()
    {

    }

    public User(String name, String surname, String ID, String password, String passwordretype, String address, String gender, String email )
    {
        this.address=address;
        this.gender=gender;
        this.ID=ID;
        this.passwordretype=passwordretype;
        this.password=password;
        this.name=name;
        this.surname=surname;
        this.email=email;
    }
}
