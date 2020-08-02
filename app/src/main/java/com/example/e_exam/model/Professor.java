package com.example.e_exam.model;

public class Professor {
    String Name;
    String Name_arabic;

    public Professor() {
    }

    public Professor(String name, String name_arabic) {
        Name = name;
        Name_arabic = name_arabic;
    }

    public String getName() {
        return Name;
    }
      public void setName(String name) {
        Name = name;
    }

    public String getName_arabic() {
        return Name_arabic;
    }

    public void setName_arabic(String name_arabic) {
        Name_arabic = name_arabic;
    }
}
