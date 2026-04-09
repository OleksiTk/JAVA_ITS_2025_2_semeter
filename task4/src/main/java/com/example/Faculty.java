package com.example;
import java.util.List;

public class Faculty {
    private String name;
    private List<Student> students; // Типізована колекція замість масиву

    public Faculty(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }
    
    @Override
    public String toString() {
        return "Факультет: " + name;
    }
}