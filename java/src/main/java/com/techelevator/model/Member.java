package com.techelevator.model;

public class Member {

    private int id;
    private boolean isChild;
    private int userId;
    private String firstName;
    private String lastInitial;
    private String avatarId;
    private String pin;

    public Member(){};

    public Member(int id, boolean isChild, int userId, String firstName, String lastInitial, String avatarId, String pin) {
        this.id = id;
        this.isChild = isChild;
        this.userId = userId;
        this.firstName = firstName;
        this.lastInitial = lastInitial;
        this.avatarId = avatarId;
        this.pin = pin;
    }



    public int getId() {
        return id;
    }

    public void setId(int id){this.id = id;}

    public boolean isChild() {
        return isChild;
    }

    public void setChild(boolean child) {
        isChild = child;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id){this.userId = id;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastInitial() {
        return lastInitial;
    }

    public void setLastInitial(String lastInitial) {
        this.lastInitial = lastInitial;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
