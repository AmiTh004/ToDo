package com.example.ToDo.models;

public class Person {
    
    String name;
    int id;

    public Person(String name, int id){
        setId(id);
        setName(name);
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
