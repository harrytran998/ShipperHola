/*
 * Copyright © 2017 XVideos Team
 */
package model;

import java.sql.Date;

/**
 *
 * @author Le Cao Nguyen
 */
public class Student {
    private String id;
    private String name;
    private boolean gender;
    private Date dateOfBirth;

    public Student() {
    }

    public Student(String id, String name, boolean gender, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + '}';
    }
    
    
}
