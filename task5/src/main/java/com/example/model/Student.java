package com.example.model;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String group;
    private final double averageGrade;

    public Student(String firstName, String lastName, String group, double averageGrade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.averageGrade = averageGrade;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGroup() {
        return group;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + group + "), average: " + averageGrade;
    }
}
