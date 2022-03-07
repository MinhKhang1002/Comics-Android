package com.example.comics.Models;

public class Genre {
    private String title;
    private String endpoint;
    private String description;

    public Genre(String title, String endpoint, String description) {
        this.title = title;
        this.endpoint = endpoint;
        this.description = description;
    }
    public Genre(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
