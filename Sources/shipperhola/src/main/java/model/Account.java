/*
 * Copyright © 2017 XVideos Team
 */
package model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Account implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private boolean gender;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private String address;
    private String facebookId;
    private String role;

    public Account() {
    }

    public Account(Integer id, String username, String password, String fullName, boolean gender, Date dateOfBirth, String email, String phoneNumber, String address, String facebookId, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.facebookId = facebookId;
        this.role = role;
    }

    public Account(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", username=" + username + ", password=" + password + ", fullName=" + fullName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", facebookId=" + facebookId + ", role=" + role + '}';
    }
}
