package com.example.peer_evaluation_project.model;

public class Student {
    private String name, rnmuber,section;

    public Student() {
    }

    public Student(String name, String rnmuber, String section) {
        this.name = name;
        this.rnmuber = rnmuber;
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRnmuber() {
        return rnmuber;
    }

    public void setRnmuber(String rnmuber) {
        this.rnmuber = rnmuber;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
