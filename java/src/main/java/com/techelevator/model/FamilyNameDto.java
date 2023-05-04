package com.techelevator.model;

public class FamilyNameDto {

    private int id;
    private String familyName;

    public FamilyNameDto(int id, String familyName) {
        this.id = id;
        this.familyName = familyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
