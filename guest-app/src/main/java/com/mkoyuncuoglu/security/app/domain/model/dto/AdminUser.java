package com.mkoyuncuoglu.security.app.domain.model.dto;

import java.io.Serializable;

public class AdminUser implements Serializable {

    private static final long serialVersionUID = -4642751308478319663L;
    
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;
    private String gender;
    private String state;
    private String job;
    private String hobbies;
    private String education;
    private String userPassword;

    public AdminUser() {
    }

    public AdminUser(long id, String userName, String firstName, String lastName,
        String phoneNumber, int age, String gender, String state, String job,
        String hobbies, String education, String userPassword) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
        this.state = state;
        this.job = job;
        this.hobbies = hobbies;
        this.education = education;
        this.userPassword = userPassword;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getState() {
        return state;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
