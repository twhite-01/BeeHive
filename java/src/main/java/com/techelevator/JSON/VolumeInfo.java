package com.techelevator.JSON;



import java.util.ArrayList;

public class VolumeInfo {

    private String title;
    private ArrayList<String> authors;
    private String description;
    private ImageLinks imageLinks;
    private ArrayList<IndustryIdentifier> industryIdentifiers;

    public VolumeInfo(){};

    public VolumeInfo(String title, ArrayList<String> authors, String description, ImageLinks imageLinks) {
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.imageLinks = imageLinks;
    }

    public VolumeInfo(String title, ArrayList<String> authors, String description, ImageLinks imageLinks, ArrayList<IndustryIdentifier> industryIdentifiers) {
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.imageLinks = imageLinks;
        this.industryIdentifiers = industryIdentifiers;
    }

    public ArrayList<IndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(ArrayList<IndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }
}
