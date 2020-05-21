package com.kennysohl.todo;

public class ToDo {
    private Integer id;
    private String text;

    public ToDo(String text) {
        this.text = text;
    }

    public ToDo(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {return id;}
    public String getText() {return text;}

}
