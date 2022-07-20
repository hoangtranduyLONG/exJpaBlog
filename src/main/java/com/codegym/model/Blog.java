package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String content;
    private String dateTime;
private String img;
    @ManyToOne
    @JsonManagedReference(value = "category-blog")
    private Category category;

    public Blog() {
    }

    public Blog(Long id, String name, String content,String img) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.img=img;
    }

    public Blog(Long id, String name, String content, Category category) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.category = category;
    }

    public Blog(Long id, String name, String content, String dateTime, Category category) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.dateTime = dateTime;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}