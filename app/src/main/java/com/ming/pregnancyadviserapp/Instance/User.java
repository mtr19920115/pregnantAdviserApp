package com.ming.pregnancyadviserapp.Instance;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String userName;
    private String passWord;
    private int zipCode;
    private String firstName;
    private String lastName;

    public User() {
    }

    public User(int id,String userName,String passWord,int zipCode,String firstName,String lastName)
    {
        this.id=id;
        this.userName=userName;
        this.passWord=passWord;
        this.zipCode=zipCode;
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
