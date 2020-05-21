package com.kennysohl.todo;

import javax.persistence.*;

import org.hibernate.annotations.Table;

@Entity
@Table(name = "todos")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "text", nullable = false, length = 128)
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
