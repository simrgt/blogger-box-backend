package com.dauphine.blogger.models;

import java.sql.Timestamp;
import java.util.UUID;

public class Post {
    private UUID id;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Category category;

    public Post(UUID id, String title, String content, Timestamp createdAt, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Category getCategory() {
        return category;
    }
}
