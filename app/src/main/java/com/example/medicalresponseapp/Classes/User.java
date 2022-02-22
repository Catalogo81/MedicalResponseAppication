package com.example.medicalresponseapp.Classes;

public class User {

    public String stringID, name, surname, id, address, gender, email;

    public User()
    {

    }

    public User(String stringID, String name, String surname, String id, String address, String gender, String email )
    {
        this.stringID = stringID;
        this.address = address;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getStringID() {
        return stringID;
    }

    public void setStringID(String stringID) {
        this.stringID = stringID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
