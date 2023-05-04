package com.techelevator.JSON;

import org.springframework.data.relational.core.sql.In;

public class IndustryIdentifier{

    private String type;
    private String identifier;

    public IndustryIdentifier(){};

    public IndustryIdentifier(String type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public IndustryIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
