package com.example.comics.Models;

public class Chapter {
    private String chapter_endpoint;
    private String book_endpoint;
    private String title;
    private String time;

    public Chapter() {
    }

    public String getChapter_endpoint() {
        return chapter_endpoint;
    }

    public void setChapter_endpoint(String chapter_endpoint) {
        this.chapter_endpoint = chapter_endpoint;
    }

    public String getBook_endpoint() {
        return book_endpoint;
    }

    public void setBook_endpoint(String book_endpoint) {
        this.book_endpoint = book_endpoint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
