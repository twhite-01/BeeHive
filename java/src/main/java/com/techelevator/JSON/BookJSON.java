package com.techelevator.JSON;

public class BookJSON {
    private String title;
    private String author;
    private String ISBN;
    private String coverUrl;

    public BookJSON(){};

    public BookJSON(String title, String author, String coverUrl) {
        this.title = title;
        this.author = author;
        this.coverUrl = coverUrl;
    }

    public BookJSON(String title, String author, String ISBN, String coverUrl) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.coverUrl = coverUrl;
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

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
