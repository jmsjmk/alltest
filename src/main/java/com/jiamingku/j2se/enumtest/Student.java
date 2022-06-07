package com.jiamingku.j2se.enumtest;

public class Student {
    private String firstName;
    private String lastName;
    private Grade grade;
    private Enum2 enum2;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return new StringBuffer(firstName)
                .append(" ")
                .append(lastName)
                .toString();
    }

    public void assignGrade(Grade grade) {
        this.grade = grade;
    }

    public void assignEnum2(Enum2 enum2) {
        this.enum2 = enum2;
    }

    public Grade getGrade() {
        return grade;
    }

    public Enum2 getEnum2() {
        return enum2;
    }

    public void setEnum2(Enum2 enum2) {
        this.enum2 = enum2;
    }
}