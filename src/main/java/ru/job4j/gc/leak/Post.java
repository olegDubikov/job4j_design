package ru.job4j.gc.leak;

import java.util.List;

public class Post {
    private int id;

    private String text;

    private List<Comment> comments;


    public Post(String text, List<Comment> comments) {
        this.text = text;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}