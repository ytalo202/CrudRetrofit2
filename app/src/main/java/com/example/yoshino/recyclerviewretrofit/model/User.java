package com.example.yoshino.recyclerviewretrofit.model;

public class User {

    private Integer id;
    private String name;
    private String date;
    private String nick;

    public User(String name, String date, String nick) {
        this.name = name;
        this.date = date;
        this.nick = nick;
    }

    public User(Integer id, String name, String date, String nick) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.nick = nick;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
