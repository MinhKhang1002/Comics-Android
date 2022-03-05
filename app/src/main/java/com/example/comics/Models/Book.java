package com.example.comics.Models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
    private String endpoint;
    private String title;
    private String author;
    private String thumb;
    private String theme;
    private String description;
    private String type;
    private float rating;
    private float rate_count;
    private int status;
    private int search_number;
    private String view;
    private List<Book> books = new ArrayList<>();

    public void setBooks(List<Book> books) {
        this.books = books;
    }


    public List<Book> getBooks() {
        return books;
    }
    public Book(){

    }

    public Book(String endpoint, String title, String author, String thumb, String theme, String description, String type, float rating, float rate_count, int status, int search_number, String view) {
        this.endpoint = endpoint;
        this.title = title;
        this.author = author;
        this.thumb = thumb;
        this.theme = theme;
        this.description = description;
        this.type = type;
        this.rating = rating;
        this.rate_count = rate_count;
        this.status = status;
        this.search_number = search_number;
        this.view = view;
    }


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRate_count() {
        return rate_count;
    }

    public void setRate_count(float rate_count) {
        this.rate_count = rate_count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSearch_number() {
        return search_number;
    }

    public void setSearch_number(int search_number) {
        this.search_number = search_number;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
