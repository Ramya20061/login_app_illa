package com.hnccbits.loginapp;

public class UserModel {

    private String name;
    private String email;
    private String city;
    private int age;

    public UserModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserModel(String name, String email, String city, int age) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.age = age;
    }


}
