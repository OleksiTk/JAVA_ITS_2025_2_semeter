package com.example;
import java.util.List;

public class Institute {
    private String name;
    private List<Faculty> faculties; // Типізована колекція

    public Institute(String name, List<Faculty> faculties) {
        this.name = name;
        this.faculties = faculties;
    }

    public String getName() {
        return name;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }
}