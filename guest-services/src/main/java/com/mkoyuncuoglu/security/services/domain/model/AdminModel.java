package com.mkoyuncuoglu.security.services.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mkoyuncuoglu.security.services.domain.model.dto.AdminUser;

public class AdminModel {

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

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @JsonIgnore
    public AdminUser translateModelToAdminUser() {
        AdminUser adminUser = new AdminUser();
        adminUser.setUserName(this.userName);
        adminUser.setFirstName(this.firstName);
        adminUser.setLastName(this.lastName);
        adminUser.setPhoneNumber(this.phoneNumber);
        adminUser.setAge(this.age);
        adminUser.setGender(this.gender);
        adminUser.setState(this.state);
        adminUser.setJob(this.job);
        adminUser.setHobbies(this.hobbies);
        adminUser.setEducation(this.education);
        adminUser.setUserPassword(this.userPassword);
        return adminUser;
    }
}
